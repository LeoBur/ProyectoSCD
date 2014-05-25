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

	private PacienteDao pacienteDao;
	
	@Override
	public void setPacienteDao(PacienteDao pacienteDao) {
		this.dao = pacienteDao;
		this.pacienteDao = pacienteDao;
	}

	@Override
	public List<Paciente> getPacientesByTipo(TipoDiabetes tipo) {
		return pacienteDao.loadPacientesByTipo(tipo);
	}
	
	@Override
	public List<Paciente> getPacientes(){
		return pacienteDao.getPacientes();
	}

}
