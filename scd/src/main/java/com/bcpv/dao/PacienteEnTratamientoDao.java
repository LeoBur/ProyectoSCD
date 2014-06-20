package com.bcpv.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.bcpv.model.PacienteEnTratamiento;

public interface PacienteEnTratamientoDao extends GenericDao<PacienteEnTratamiento, Long>{
	
	PacienteEnTratamiento loadPacienteEnTratamientoById(Long idPacienteEnTratamiento) throws EntityNotFoundException;
	
	PacienteEnTratamiento savePacienteEnTratamiento(PacienteEnTratamiento pacienteEnTratamiento);

}
