package com.bcpv.dao.hibernate;

import javax.persistence.EntityNotFoundException;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bcpv.dao.DiaDietaDao;
import com.bcpv.model.DiaDieta;

@Repository("diaDietaDao")
public class DiaDietaDaoHibernate extends GenericDaoHibernate<DiaDieta, Long> implements DiaDietaDao {
	
	public DiaDietaDaoHibernate(){
		super(DiaDieta.class);
	}

	@Override
	public DiaDieta loadDiaDietaById(Long idDiaDieta)
			throws EntityNotFoundException {
		DiaDieta diaDieta = (DiaDieta) getSession().createCriteria(DiaDieta.class).add(Restrictions.eq("idDiaDieta", idDiaDieta));
		if (diaDieta == null){
			throw new EntityNotFoundException("DiaDieta id: "+idDiaDieta+" not found...");
		} else{
			return diaDieta;
		}
	}

	@Override
	public DiaDieta saveDiaDieta(DiaDieta diaDieta) {
		if(log.isDebugEnabled()){
			log.debug("DiaDieta id: "+diaDieta.getIdDiaDieta());
		}
		getSession().saveOrUpdate(diaDieta);
		getSession().flush();
		return diaDieta;
	}

}
