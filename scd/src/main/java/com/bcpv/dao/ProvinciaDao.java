package com.bcpv.dao;

import java.util.List;

import com.bcpv.model.Provincia;

public interface ProvinciaDao extends GenericDao<Provincia, Long> {
	
	Provincia loadProvinciaByNombre(String nombre);
	
	List<Provincia> getProvincias();
	
	Provincia saveProvincia(Provincia provincia);

}
