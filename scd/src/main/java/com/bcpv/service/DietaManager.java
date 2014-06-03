package com.bcpv.service;

import com.bcpv.dao.DietaDao;
import com.bcpv.model.Dieta;

public interface DietaManager extends GenericManager<Dieta, Long>{
	
	void setDietaDao(DietaDao dietaDao);

}
