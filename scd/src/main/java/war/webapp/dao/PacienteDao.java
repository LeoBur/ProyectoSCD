package war.webapp.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import war.webapp.model.Paciente;
import war.webapp.model.TipoDiabetes;

public interface PacienteDao extends GenericDao<Paciente, Long>{
	
	Paciente loadPacienteByDNI(Long dni) throws EntityNotFoundException;

    List<Paciente> getPacientes();
    
    Paciente savePaciente(Paciente paciente);
	
	List<Paciente> loadPacientesByTipo (TipoDiabetes tipo);
	
}
