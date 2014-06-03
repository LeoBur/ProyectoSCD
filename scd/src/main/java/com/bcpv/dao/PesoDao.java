package com.bcpv.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.bcpv.model.Peso;

public interface PesoDao extends GenericDao<Peso, Long>{
	
	Peso loadPesoById(Long id) throws EntityNotFoundException;
	
	List<Peso> getPesos();
	
	Peso savePeso(Peso peso);
}
