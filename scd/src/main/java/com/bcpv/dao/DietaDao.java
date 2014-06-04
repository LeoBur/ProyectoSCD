package com.bcpv.dao;

import com.bcpv.model.Dieta;

import java.util.List;

import javax.persistence.EntityNotFoundException;

public interface DietaDao extends GenericDao<Dieta, Long>{

	Dieta loadDietaById(Long idDieta) throws EntityNotFoundException;
	
	List<Dieta> getDietas();
	
	Dieta saveDieta(Dieta dieta);
}
