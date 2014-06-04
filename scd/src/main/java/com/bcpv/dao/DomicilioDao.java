package com.bcpv.dao;

import javax.persistence.EntityNotFoundException;

import com.bcpv.model.Domicilio;

public interface DomicilioDao extends GenericDao<Domicilio, Long>{
	
	Domicilio loadDomicilioById(Long id)throws EntityNotFoundException;
	
	Domicilio saveDomicilio(Domicilio domicilio);

}
