package war.webapp.service;

import java.util.List;

import war.webapp.dao.PacienteDao;
import war.webapp.model.Paciente;
import war.webapp.model.TipoDiabetes;

public interface PacienteManager extends PersonaManager{

	void setPacienteDao(PacienteDao pacienteDao);
	
	List<Paciente> getPacientesByTipo(TipoDiabetes tipo);
	
	List<Paciente> getPacientes();
}
