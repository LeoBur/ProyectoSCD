package war.webapp.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import war.webapp.dao.PacienteDao;
import war.webapp.model.Paciente;
import war.webapp.model.TipoDiabetes;

public interface PacienteManager extends GenericManager<Paciente, Long>{

	void setPacientesDao(PacienteDao pacienteDao);
	   
    Paciente getPaciente(Long id);

    List<Paciente> getPacientes();

    Paciente savePaciente(Paciente paciente) throws EntityExistsException;
    
    void removePaciente(Paciente paciente);
    
    void removePaciente(String id);

    List<Paciente> search(String searchTerm);
		
	List<Paciente> getPacientesByTipo(TipoDiabetes tipo);
}
