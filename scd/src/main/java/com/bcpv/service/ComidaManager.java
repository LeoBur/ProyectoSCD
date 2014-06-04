package com.bcpv.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import com.bcpv.dao.ComidaDao;
import com.bcpv.model.Comida;

public interface ComidaManager extends GenericManager<Comida, Long>{
	
	void setComidaDao(ComidaDao comidaDao);
	
	Comida getComida(Long idComida);
	
	Comida saveComida (Comida comida) throws EntityExistsException;
	
	void removeComida(Comida comida);
	
	void removeComida(String idComida);
	
	List<Comida> search(String searchTerm);

}
