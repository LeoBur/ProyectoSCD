package com.bcpv.service;

import javax.persistence.EntityExistsException;

import com.bcpv.dao.DietaDao;
import com.bcpv.model.Dieta;

public interface DietaManager extends GenericManager<Dieta, Long>{
	
	void setDietaDao(DietaDao dietaDao);
	
	Dieta getDieta(Long idDieta);
	
	Dieta saveDieta(Dieta dieta) throws EntityExistsException;
	
	void removeDieta(Dieta dieta);
	
	void removeDieta(String idDieta);

}
