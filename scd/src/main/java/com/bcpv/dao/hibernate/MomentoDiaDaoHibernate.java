package com.bcpv.dao.hibernate;

import javax.persistence.EntityNotFoundException;

import org.hibernate.criterion.Restrictions;

import com.bcpv.dao.MomentoDiaDao;
import com.bcpv.model.MomentoDia;

public class MomentoDiaDaoHibernate extends GenericDaoHibernate<MomentoDia, Long> implements MomentoDiaDao{

	public MomentoDiaDaoHibernate(){
		super(MomentoDia.class);
	}

	@Override
	public MomentoDia loadMomentoDiaById(Long idMomentoD) throws EntityNotFoundException{
		MomentoDia momentoD = (MomentoDia) getSession().createCriteria(MomentoDia.class).add(Restrictions.eq("idMomentoD", idMomentoD));
		if (momentoD == null){
			throw new EntityNotFoundException("MomentoDia id: "+idMomentoD+" not found...");
		} else{
			return momentoD;
		}
	}

	@Override
	public MomentoDia saveMomentoD(MomentoDia momentoD) {
		if(log.isDebugEnabled()){
			log.debug("MomentoDia id:"+momentoD.getIdMomentoD());
		}
		getSession().saveOrUpdate(momentoD);
		getSession().flush();
		return momentoD;
	}

}
