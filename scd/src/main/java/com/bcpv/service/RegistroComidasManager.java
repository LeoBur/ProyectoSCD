package com.bcpv.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import com.bcpv.dao.RegistroComidasDao;
import com.bcpv.model.RegistroComidas;


public interface RegistroComidasManager extends GenericManager<RegistroComidas, Long>{

	void setRegistroComidasDao(RegistroComidasDao registroComidasDao);
	
	RegistroComidas getRegistroComidas(Long idRegComidas);
	
	RegistroComidas saveRegistroComidas (RegistroComidas regComidas) throws EntityExistsException;
	
	void removeRegistroComidas(RegistroComidas regComidas);
	
	void removeRegistroComidas(String idRegComidas);
	
	List<RegistroComidas> search(String searchTerm);
}
