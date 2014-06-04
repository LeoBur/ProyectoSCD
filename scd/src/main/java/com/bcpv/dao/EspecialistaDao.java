package com.bcpv.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.bcpv.model.Especialista;

public interface EspecialistaDao extends GenericDao<Especialista, Long>{
	
	Especialista loadEspecialistaByDNI(Long dni) throws EntityNotFoundException;

    List<Especialista> getEspecialistas();
    
    Especialista saveEspecialista(Especialista especialista);
	
	List<Especialista> getEspecialistaByTipo (String tipo);

}
