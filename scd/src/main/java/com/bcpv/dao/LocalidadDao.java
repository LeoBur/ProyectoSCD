package com.bcpv.dao;

import java.util.List;

import com.bcpv.model.Localidad;

public interface LocalidadDao extends GenericDao<Localidad, Long> {
	
	Localidad loadLocalidadByNombre(String nombre);
	
	List<Localidad> getLocalidades();
	
	Localidad saveLocalidad(Localidad localidad);

    List<Localidad> getByNombreYProv(String nombre, String provincia);

}
