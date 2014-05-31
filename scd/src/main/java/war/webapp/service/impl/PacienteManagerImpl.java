package war.webapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import war.webapp.dao.PacienteDao;
import war.webapp.model.Paciente;
import war.webapp.model.TipoDiabetes;
import war.webapp.service.PacienteManager;
import war.webapp.service.PacienteService;

@Service("pacienteManager")
@WebService(serviceName = "PacienteService", endpointInterface = "war.webapp.service.PacienteService")
public class PacienteManagerImpl extends GenericManagerImpl<Paciente, Long> implements PacienteService, PacienteManager{

	private PacienteDao pacientesDao;
	
	@Autowired
	public void setPacientesDao(PacienteDao pacienteDao) {
		this.dao = pacienteDao;
		this.pacientesDao = pacienteDao;
	}

	@Override
	public Paciente getPaciente(String id) {
		return pacientesDao.get(new Long(id));
	}

	@Override
	public Paciente getPaciente(Long id) {
		return pacientesDao.get(id);
	}

	@Override
	public List<Paciente> getPacientes() {
		if (pacientesDao!=null){
			return pacientesDao.getAllDistinct();
		}
		return new ArrayList<Paciente>();
	}

	@Override
	public Paciente savePaciente(final Paciente paciente)
			throws EntityExistsException {
		try {
			return pacientesDao.savePaciente(paciente);
		} catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("La paciente ya existe");
		}
	}

	@Override
	public List<Paciente> search(String searchTerm) {
		return super.search(searchTerm, Paciente.class);
	}

	@Override
	public void removePaciente(Paciente paciente) {
		log.debug("removing paciente: " + paciente.getNombre() + paciente.getApellido());
		pacientesDao.remove(paciente);
	}

	@Override
	public void removePaciente(String id) {
		log.debug("removing paciente: " + id);
		pacientesDao.remove(new Long(id));
		
	}
	
	@Override
	public List<Paciente> getPacientesByTipo(TipoDiabetes tipo) {
		return pacientesDao.loadPacientesByTipo(tipo);
	}

	@Override
	public List<Paciente> loadPacientesByApellido(String apellido) {
		return pacientesDao.loadPacientesByApellido(apellido);
	}	

}
