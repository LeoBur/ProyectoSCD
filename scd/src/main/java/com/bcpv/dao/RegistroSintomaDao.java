package com.bcpv.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.bcpv.model.RegistroSintoma;

public interface RegistroSintomaDao extends GenericDao<RegistroSintoma, Long>{

	RegistroSintoma loadRegistroSintomaById(Long idRegSintoma) throws EntityNotFoundException;
	
	List<RegistroSintoma> getRegistroSintoma();
	
	RegistroSintoma saveRegistroSintoma(RegistroSintoma regSintoma);
}
