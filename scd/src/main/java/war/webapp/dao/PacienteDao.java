package war.webapp.dao;

import java.util.List;

import war.webapp.model.Paciente;
import war.webapp.model.TipoDiabetes;

public interface PacienteDao extends PersonaDao{
	
	List<Paciente> loadPacientesByTipo (TipoDiabetes tipo);
	
	List<Paciente> getPacientes();
}
