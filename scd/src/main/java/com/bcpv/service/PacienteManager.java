package com.bcpv.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import com.bcpv.dao.PacienteDao;
import com.bcpv.model.Paciente;
import com.bcpv.model.Persona;
import com.bcpv.model.TipoDiabetes;

public interface PacienteManager extends GenericManager<Paciente, Long>{

	void setPacientesDao(PacienteDao pacienteDao);
	   
    Paciente getPaciente(Long dni);

    List<Paciente> getPacientes();

    Paciente savePaciente(Paciente paciente) throws EntityExistsException;
    
    void removePaciente(Paciente paciente);
    
    void removePaciente(Long id);

    List<Paciente> search(String searchTerm);
		
	List<Paciente> getPacientesByTipo(TipoDiabetes tipo);
	
	List<Paciente> loadPacientesByApellido(List<Persona> persList);
	
	Paciente getPacienteByUsername(String username);
	
	Paciente loadPacienteByDNI(Persona persona);
}
