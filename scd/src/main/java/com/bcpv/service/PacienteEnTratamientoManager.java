package com.bcpv.service;

import java.util.List;

import com.bcpv.dao.PacienteEnTratamientoDao;
import com.bcpv.model.PacienteEnTratamiento;

public interface PacienteEnTratamientoManager extends GenericManager<PacienteEnTratamiento, Long>{
	
	void setPacienteEnTratamientoDao(PacienteEnTratamientoDao pacienteEnTratamientoDao);
	
	PacienteEnTratamiento getPacienteEnTratamiento(Long idPacienteEnTratamiento);
	
	PacienteEnTratamiento savePacienteEnTratamiento(PacienteEnTratamiento pacienteEnTratamiento);
	
	void removePacienteEnTratamiento(PacienteEnTratamiento pacienteEnTratamiento);
	
	void removePacienteEnTratamiento(Long idPacienteEnTratamiento);
	
	List<PacienteEnTratamiento> search(String searchTerm);

}
