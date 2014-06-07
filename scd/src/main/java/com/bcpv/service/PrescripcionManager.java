package com.bcpv.service;

import java.util.List;

import com.bcpv.dao.PrescripcionDao;
import com.bcpv.model.Prescripcion;

public interface PrescripcionManager extends GenericManager<Prescripcion, Long>{
	
	void setPrescripcionDao(PrescripcionDao prescripcionDao);
	
	Prescripcion getPrescripcion(Long idPrescripcion);
	
	List<Prescripcion> getPrescripcionesByIdTratamiento(Long idTratamiento);
	
	Prescripcion savePrescripcion(Prescripcion prescripcion);
	
	void removePrescripcion(Prescripcion prescripcion);
	
	void removePrescripcion(Long idPrescripcion);
	
	List<Prescripcion> search(String searchTerm);

}
