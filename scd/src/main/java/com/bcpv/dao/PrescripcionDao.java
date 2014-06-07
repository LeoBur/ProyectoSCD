package com.bcpv.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.bcpv.model.Prescripcion;

public interface PrescripcionDao extends GenericDao<Prescripcion, Long>{
	
	Prescripcion loadPrescripcionById(Long idPrescripcion) throws EntityNotFoundException;
	
	List<Prescripcion> getPrescripcionesByIdTratamiento(Long idTratamiento);
	
	Prescripcion savePrescripcion(Prescripcion prescripcion);	

}
