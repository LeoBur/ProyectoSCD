package com.bcpv.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.bcpv.model.Sintoma;

public interface SintomaDao extends GenericDao<Sintoma, Long> {
	
	Sintoma loadSintomaById(Long idSintoma) throws EntityNotFoundException;
	
	List<Sintoma> getSintomas();
	
	Sintoma saveSintoma (Sintoma sintoma);
}
