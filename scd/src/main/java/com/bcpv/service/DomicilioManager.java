package com.bcpv.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import com.bcpv.dao.DomicilioDao;
import com.bcpv.model.Domicilio;

public interface DomicilioManager extends GenericManager<Domicilio, Long>{
	
	void setDomicilioDao(DomicilioDao domicilioDao);
	
	Domicilio getDomicilio(Long idDomicilio);
	
	Domicilio saveDomicilio (Domicilio domicilio) throws EntityExistsException;
	
	void removeDomicilio(Domicilio domicilio);
	
	void removeDomicilio(String idDomicilio);
	
	List<Domicilio> search(String searchTerm);
}
