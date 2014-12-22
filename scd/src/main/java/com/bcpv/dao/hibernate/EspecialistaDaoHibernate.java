package com.bcpv.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.bcpv.model.Persona;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bcpv.dao.EspecialistaDao;
import com.bcpv.model.Especialista;
import com.bcpv.model.Especialista.TipoEspecialista;

@Repository("especialistaDao")
public class EspecialistaDaoHibernate extends GenericDaoHibernate<Especialista, Long> implements EspecialistaDao{
	
	public EspecialistaDaoHibernate() {
        super(Especialista.class);
    }
	
	@Override
	public Especialista loadEspecialistaByDNI(Long dni) throws EntityNotFoundException {
		Especialista especialista = (Especialista) getSession().createCriteria(Especialista.class).add(Restrictions.eq("dni", dni));
		if (especialista == null){
			throw new EntityNotFoundException("Especialista con DNI :" + dni + " no existe");
		} else {
			return especialista;
		}
	}
	
    @SuppressWarnings("unchecked")
    public List<Especialista> getEspecialistas() {
		Query qry = getSession().createQuery("from Especialista e2 order by upper(e2.persona.lastName)");
        return qry.list();
	}

    @Override
    public Especialista saveEspecialista(Especialista especialista) {
    	if (log.isDebugEnabled()) {
            log.debug("especialista id: " + especialista.getId());
        }
        getSession().saveOrUpdate(especialista);
        // necessary to throw a DataIntegrityViolation and catch it in MedicionManager
        getSession().flush();
        return especialista;
    }
	
	@SuppressWarnings("unchecked")
	public List<Especialista> getEspecialistaByTipo(String tipo){
		Query qry = getSession().createQuery("from Especialista e order by upper(e.tipo_esp)");
        return qry.list();
	}

    @Override
    public Especialista getEspecialistaByPersona(Persona persona) throws EntityNotFoundException {
        Query qry = getSession().createQuery("from Especialista es where  es.persona.id = :id_persona");
        qry.setParameter("id_persona", persona.getId());
        Especialista especialista = (Especialista) qry.uniqueResult();
        if (especialista == null) {
            throw new EntityNotFoundException("No existe especialista asociado a la persona");
        } else {
            return especialista;
        }
    }

    @Override
    public Especialista getEspecialista(Long matricula, TipoEspecialista tipoEspecialista) throws EntityNotFoundException {
        Query qry = getSession().createQuery("from Especialista es where es.matricula = :matricula AND es.tipo_esp = :tipoEspecialista");
        qry.setParameter("matricula",matricula);
        qry.setParameter("tipoEspecialista",tipoEspecialista);
        Especialista especialista = (Especialista) qry.uniqueResult();
        if (especialista == null) {
            throw new EntityNotFoundException("No existe especialista asociado a la persona");
        } else {
            return especialista;
        }
    }

    @Override
    public Especialista getEspecialista(Long idEspecialista) throws EntityNotFoundException {
        Query qry = getSession().createQuery("from Especialista es where  es.id = :idEspecialista");
        qry.setParameter("idEspecialista", idEspecialista);
        Especialista especialista = (Especialista) qry.uniqueResult();
        if (especialista == null) {
            throw new EntityNotFoundException("No existe el especialista");
        } else {
            return especialista;
        }
    }

}
