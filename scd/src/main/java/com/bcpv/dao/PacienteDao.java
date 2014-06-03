package com.bcpv.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.transaction.annotation.Transactional;

import com.bcpv.model.Paciente;
import com.bcpv.model.TipoDiabetes;

public interface PacienteDao extends GenericDao<Paciente, Long>{
	
	@Transactional
	Paciente loadPacienteByDNI(Long dni) throws EntityNotFoundException;

    List<Paciente> getPacientes();
    
    Paciente savePaciente(Paciente paciente);
	
    List<Paciente> loadPacientesByTipo (TipoDiabetes tipo);
    
    @Transactional
    List<Paciente> loadPacientesByApellido (String apellido);
	
}
