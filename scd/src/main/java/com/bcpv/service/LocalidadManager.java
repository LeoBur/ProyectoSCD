package com.bcpv.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import com.bcpv.dao.LocalidadDao;
import com.bcpv.model.Localidad;

public interface LocalidadManager extends GenericManager<Localidad, Long>{
	
	void setLocalidadDao(LocalidadDao localidadDao);
	   
    Localidad getLocalidad(Long id_localidad);

    List<Localidad> getLocalidades();

    Localidad saveLocalidad(Localidad localidad) throws EntityExistsException;
    
    void removeLocalidad(Localidad localidad);
    
    void removeLocalidad(String id);

    List<Localidad> search(String searchTerm);

}
