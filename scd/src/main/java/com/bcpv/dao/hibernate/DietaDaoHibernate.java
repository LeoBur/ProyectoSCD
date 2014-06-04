package com.bcpv.dao.hibernate;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import com.bcpv.dao.DietaDao;
import com.bcpv.model.Dieta;

@Repository("dietaDao")
public class DietaDaoHibernate extends GenericDaoHibernate<Dieta, Long> implements DietaDao{
	
	public DietaDaoHibernate(){
		super(Dieta.class);
	}
	
	@Override
	public Dieta loadDietaById(Long idDieta) throws EntityNotFoundException {
		Dieta dieta = (Dieta) getSession().createCriteria(Dieta.class).add(Restrictions.eq("id_dieta", idDieta));
		if (dieta == null){
			throw new EntityNotFoundException("Dieta id: "+idDieta+" not found...");
		} else{
			return dieta;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dieta> getDietas() {
		Query qry = getSession().createQuery("from Dieta d order by (d.fecha_alta) DESC");
		return qry.list();
	}

	@Override
	public Dieta saveDieta(Dieta dieta) {
		if(log.isDebugEnabled()){
			log.debug("dieta id: "+dieta.getIdDieta());
		}
		getSession().saveOrUpdate(dieta);
		getSession().flush();
		return dieta;
	}

}
