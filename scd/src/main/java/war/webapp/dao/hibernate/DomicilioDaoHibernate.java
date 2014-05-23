package war.webapp.dao.hibernate;

import javax.persistence.EntityNotFoundException;

import org.hibernate.criterion.Restrictions;

import war.webapp.dao.DomicilioDao;
import war.webapp.model.Domicilio;

public class DomicilioDaoHibernate extends GenericDaoHibernate<Domicilio, Long> implements DomicilioDao{

	public DomicilioDaoHibernate() {
		super(Domicilio.class);
	}

	@Override
	public Domicilio loadDomicilioById(Long id) throws EntityNotFoundException {
		Domicilio domicilio = (Domicilio) getSession().createCriteria(Domicilio.class).add(Restrictions.eq("id", id));
		if (domicilio==null){
			throw new EntityNotFoundException("domicilio id: "+id+" not found...");
		}
		else
			return domicilio;
	}

	@Override
	public Domicilio saveDomicilio(Domicilio domicilio) {
		if(log.isDebugEnabled()){
			log.debug("domicilio id: "+ domicilio.getId());
		}
		getSession().saveOrUpdate(domicilio);
		getSession().flush();
		return domicilio;
	}

}