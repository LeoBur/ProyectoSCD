package com.bcpv.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.bcpv.dao.DietaDao;
import com.bcpv.model.Dieta;

@Repository("dietaDao")
public class DietaDaoHibernate extends GenericDaoHibernate<Dieta, Long> implements DietaDao{
	
	public DietaDaoHibernate(){
		super(Dieta.class);
	}

}
