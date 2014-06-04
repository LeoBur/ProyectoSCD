package com.bcpv.dao.hibernate;

import javax.persistence.EntityNotFoundException;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bcpv.dao.DomicilioDao;
import com.bcpv.model.Domicilio;

@Repository("domicilioDao")
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
