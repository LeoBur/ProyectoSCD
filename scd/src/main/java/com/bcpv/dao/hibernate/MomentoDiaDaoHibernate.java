package com.bcpv.dao.hibernate;

import javax.persistence.EntityNotFoundException;

import com.bcpv.dao.ComidaDao;
import com.bcpv.model.Comida;
import org.hibernate.criterion.Restrictions;

import com.bcpv.dao.MomentoDiaDao;
import com.bcpv.model.MomentoDia;
import org.springframework.beans.factory.annotation.Autowired;

public class MomentoDiaDaoHibernate extends GenericDaoHibernate<MomentoDia, Long> implements MomentoDiaDao{

	public MomentoDiaDaoHibernate(){
		super(MomentoDia.class);
	}

    @Autowired
    private ComidaDao comidaDao;

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
        /*for (Comida comida : momentoD.getComidas()) {
            comidaDao.saveComida(comida);
        }*/
		getSession().saveOrUpdate(momentoD);
		return momentoD;
	}

}
