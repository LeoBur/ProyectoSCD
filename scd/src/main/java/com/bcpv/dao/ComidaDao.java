package com.bcpv.dao;

import javax.persistence.EntityNotFoundException;

import com.bcpv.model.Comida;

public interface ComidaDao extends GenericDao<Comida, Long> {
	
	Comida loadComidaById(Long idComida) throws EntityNotFoundException;
	
	Comida saveComida(Comida comida);
	

}
