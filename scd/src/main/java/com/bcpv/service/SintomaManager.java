package com.bcpv.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import com.bcpv.dao.SintomaDao;
import com.bcpv.model.Sintoma;

public interface SintomaManager extends GenericManager<Sintoma, Long> {
	
	void setSintomaDao(SintomaDao sintomaDao);
	
	Sintoma getSintoma(Long idSintoma);
	
	List<Sintoma> getSintomas();
	
	Sintoma saveSintoma(Sintoma sintoma) throws EntityExistsException;
	
	void removeSintoma(Sintoma sintoma);
	
	void removeSintoma(Long idSintoma);
	
	List<Sintoma> search(String searchTerm);
	
	Sintoma getByNombre(String nombre)
;
}
