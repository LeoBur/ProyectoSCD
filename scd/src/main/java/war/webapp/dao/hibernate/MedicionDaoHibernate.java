package war.webapp.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import war.webapp.dao.MedicionDao;
import war.webapp.model.Medicion;

@Repository("medicionDao")
public class MedicionDaoHibernate extends GenericDaoHibernate<Medicion, Long> implements MedicionDao{

	/**
     * Constructor that sets the entity to User.class.
     */
    public MedicionDaoHibernate() {
        super(Medicion.class);
    }

	@Override
	public Medicion loadMedicionById(Long id) throws EntityNotFoundException {
		 Medicion medicion = (Medicion) getSession().createCriteria(Medicion.class).add(Restrictions.eq("id_medicion", id));
	        if (medicion == null) {
	            throw new EntityNotFoundException("medicion id: '" + id + "' not found...");
	        } else {
	            return medicion;
	        }
	}

    @SuppressWarnings("unchecked")
	public List<Medicion> getMediciones() {
		Query qry = getSession().createQuery("from Medicion m order by upper(m.f_medicion)");
        return qry.list();
	}

	@Override
	public Medicion saveMedicion(Medicion medicion) {
		
		if (log.isDebugEnabled()) {
            log.debug("medicion id: " + medicion.getId_medicion());
        }
        getSession().saveOrUpdate(medicion);
        // necessary to throw a DataIntegrityViolation and catch it in MedicionManager
        getSession().flush();
        return medicion;
	}
	
	/**
     * Overridden simply to call the saveUser method. This is happening
     * because saveUser flushes the session and saveObject of BaseDaoHibernate
     * does not.
     *
     * @param user the user to save
     * @return the modified user (with a primary key set if they're new)
     */
    @Override
    public Medicion save(Medicion medicion) {
        return this.saveMedicion(medicion);
    }
}
