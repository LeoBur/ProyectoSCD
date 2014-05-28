package war.webapp.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import war.webapp.dao.PacienteDao;
import war.webapp.model.Paciente;
import war.webapp.model.TipoDiabetes;
import war.webapp.service.PacienteManager;
import war.webapp.service.PacienteService;

@Service("pacienteManager")
@WebService(serviceName = "PacienteService", endpointInterface = "war.webapp.service.PacienteService")
public class PacienteManagerImpl extends PersonaManagerImpl implements PacienteService, PacienteManager{

	private PacienteDao pacientesDao;
	
	@Override
	public void setPacientesDao(PacienteDao pacienteDao) {
		this.dao = pacienteDao;
		this.pacientesDao = pacienteDao;
	}

	@Override
	public List<Paciente> getPacientesByTipo(TipoDiabetes tipo) {
		return pacientesDao.loadPacientesByTipo(tipo);
	}
	
	@Override
	public List<Paciente> getPacientes(){
		return pacientesDao.getPacientes();
	}

}
