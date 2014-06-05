package com.bcpv.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import com.bcpv.dao.RegistroSintomaDao;
import com.bcpv.model.RegistroSintoma;

public interface RegistroSintomaManager extends GenericManager<RegistroSintoma, Long>{

	void setRegistroSintomaDao(RegistroSintomaDao registroSintomaDao);
	
	RegistroSintoma getRegistroSintoma(Long idRegSintoma);
	
	RegistroSintoma saveRegistroSintoma (RegistroSintoma regSintoma) throws EntityExistsException;
	
	void removeRegistroSintoma(RegistroSintoma regSintoma);
	
	void removeRegistroSintoma(String idRegSintoma);
	
	List<RegistroSintoma> search(String searchTerm);
}
