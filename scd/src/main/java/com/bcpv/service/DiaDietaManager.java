package com.bcpv.service;

import javax.persistence.EntityExistsException;

import com.bcpv.dao.DiaDietaDao;
import com.bcpv.model.DiaDieta;

public interface DiaDietaManager extends GenericManager<DiaDieta, Long>{
	
	void setDiaDietaDao(DiaDietaDao diaDietaDao);
	
	DiaDieta getDiaDieta(Long idDiaDieta);
	
	DiaDieta saveDiaDieta(DiaDieta diaDietaD) throws EntityExistsException;
	
	void removeDiaDieta(DiaDieta diaDieta);
	
	void removeDiaDieta(String idDiaDieta);

}
