package com.bcpv.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bcpv.dao.LocalidadDao;
import com.bcpv.model.Localidad;

@Repository("localidadDao")
public class LocalidadDaoHibernate extends GenericDaoHibernate<Localidad, Long> implements LocalidadDao {
	
	public LocalidadDaoHibernate() {
        super(Localidad.class);
    }
	
	@SuppressWarnings("unchecked")
    public List<Localidad> getLocalidades() {
        Query qry = getSession().createQuery("from localidades l order by upper(l.nombre)");
        return qry.list();
    }

    
    public Localidad saveLocalidad(Localidad localidad) {
        if (log.isDebugEnabled()) {
            log.debug("localidad id: " + localidad.getId());
        }
        getSession().saveOrUpdate(localidad);
        
        getSession().flush();
        return localidad;
    }
    
    @Override
    public Localidad save(Localidad localidad) {
        return this.saveLocalidad(localidad);
    }

    
    @SuppressWarnings("unchecked")
	public Localidad loadLocalidadByNombre(String nombre) throws EntityNotFoundException {
        List<Localidad> locList = getSession().createCriteria(Localidad.class).add(Restrictions.eq("nombre", nombre)).list();
        if (locList == null || locList.isEmpty()) {
            throw new EntityNotFoundException("localidad '" + nombre + "' not found...");
        } else {
            return (Localidad) locList.get(0);
        }
    }

}
