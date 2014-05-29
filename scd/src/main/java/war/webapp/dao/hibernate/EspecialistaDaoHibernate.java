package war.webapp.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import war.webapp.dao.EspecialistaDao;
import war.webapp.model.Especialista;

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
		Query qry = getSession().createQuery("from especialista e2 order by upper(e2.apellido)");
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

}
