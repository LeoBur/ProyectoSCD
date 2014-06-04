package com.bcpv.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.bcpv.model.RegistroComidas;

public interface RegistroComidasDao extends GenericDao<RegistroComidas, Long>{

	RegistroComidas loadRegistroComidasById(Long idRegComidas) throws EntityNotFoundException;
	
	List<RegistroComidas> getRegistroComidas();
	
	RegistroComidas saveRegistroComidas(RegistroComidas regComidas);
}
