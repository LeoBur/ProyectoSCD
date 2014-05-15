package war.webapp.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import war.webapp.dao.AlimentoDao;
import war.webapp.model.Alimento;

public class AlimentoDaoHibernate extends GenericDaoHibernate<Alimento, Long> implements AlimentoDao {
	
	/**
     * Constructor that sets the entity to User.class.
     */
	public AlimentoDaoHibernate(){
		super (Alimento.class);
	}

	@Override
	public Alimento loadAlimentoById(Long id) throws EntityNotFoundException {
		Alimento alimento = (Alimento) getSession().createCriteria(Alimento.class).add(Restrictions.eq("idAlimento", id));
		if (alimento == null)
			throw new EntityNotFoundException("Alimento id: "+id+" not found...");
		else return alimento;
	}



	@SuppressWarnings("unchecked")
	public List<Alimento> getAlimentos() {
		Query qry = getSession().createQuery("from Alimento m order by upper(a.nombre)");
        return qry.list();
	}

	@Override
	public Alimento saveAlimento(Alimento alimento) {
		
		if (log.isDebugEnabled()) {
            log.debug("alimento id: " + alimento.getIdAlimento());
        }
        getSession().saveOrUpdate(alimento);
        // necessary to throw a DataIntegrityViolation and catch it in MedicionManager
        getSession().flush();
        return alimento;
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
    public Alimento save(Alimento alimento) {
        return this.saveAlimento(alimento);
    }


}
