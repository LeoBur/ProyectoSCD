package com.bcpv.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bcpv.dao.RegistroSintomaDao;
import com.bcpv.model.RegistroSintoma;

@Repository("registroSintomaDao")
public class RegistroSintomaDaoHibernate extends GenericDaoHibernate<RegistroSintoma, Long> implements RegistroSintomaDao{

	public RegistroSintomaDaoHibernate(){
		super(RegistroSintoma.class);
	}
	
	@Override
	public RegistroSintoma loadRegistroSintomaById(Long idRegSintoma)
			throws EntityNotFoundException {
		RegistroSintoma regSintoma = (RegistroSintoma) getSession().createCriteria(RegistroSintoma.class).add(Restrictions.eq("id_reg_sintoma", idRegSintoma));
		if (regSintoma ==null){
			throw new EntityNotFoundException("RegistroSintoma id: "+idRegSintoma+" not found...");
		} else{
			return regSintoma;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroSintoma> getRegistroSintoma() {
		Query qry = getSession().createQuery("from registro_sintoma rs order by (rc.fch_reg_sintoma) DESC");
		return qry.list();
	}

	@Override
	public RegistroSintoma saveRegistroSintoma(RegistroSintoma regSintoma) {
		if (log.isDebugEnabled()){
			log.debug("RegistroSintoma id: "+ regSintoma.getIdRegSintoma());
		}
		getSession().saveOrUpdate(regSintoma);
		getSession().flush();
		return regSintoma;
	}
}
