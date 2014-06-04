package com.bcpv.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.bcpv.dao.PesoDao;
import com.bcpv.model.Peso;

public class PesoDaoHibernate extends GenericDaoHibernate<Peso, Long> implements PesoDao{
	
	public PesoDaoHibernate(){
		super(Peso.class);
	}

	@Override
	public Peso loadPesoById(Long id) throws EntityNotFoundException {
		Peso peso = (Peso) getSession().createCriteria(Peso.class).add(Restrictions.eq("id", id));
		if(peso==null){
			throw new EntityNotFoundException("Peso id: "+id+" not found...");
		} else {
			return peso;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Peso> getPesos() {
		Query qry = getSession().createQuery("from Peso p order by upper(p.fechaHora)");
		return qry.list();
	}

	@Override
	public Peso savePeso(Peso peso) {
		if(log.isDebugEnabled()){
			log.debug("peso id: "+peso.getId());
		}
		getSession().saveOrUpdate(peso);
		getSession().flush();
		return peso;
	}

}
