package com.bcpv.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import com.bcpv.dao.TratamientoDao;
import com.bcpv.model.Tratamiento;

public interface TratamientoManager extends GenericManager<Tratamiento, Long> {
	
	void setTratamientoDao(TratamientoDao tratamientoDao);
	
	Tratamiento getTratamiento(Long idTratamiento);
	
	List<Tratamiento> getTratamientosByIdPaciente(Long idPaciente);
	
	Tratamiento saveTratamiento(Tratamiento tratamiento) throws EntityExistsException;
	
	void removeTratamiento(Tratamiento tratamiento);
	
	void removeTratamiento(Long idTratamiento);
	
	List<Tratamiento> search(String searchTerm);

}
