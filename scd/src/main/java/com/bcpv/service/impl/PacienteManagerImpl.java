package com.bcpv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcpv.dao.PacienteDao;
import com.bcpv.model.Paciente;
import com.bcpv.model.TipoDiabetes;
import com.bcpv.service.PacienteManager;
import com.bcpv.service.PacienteService;

@Service("pacienteManager")
@WebService(serviceName = "PacienteService", endpointInterface = "com.bcpv.service.PacienteService")
@Transactional
public class PacienteManagerImpl extends GenericManagerImpl<Paciente, Long> implements PacienteService, PacienteManager{

	private PacienteDao pacientesDao;
	
	@Override
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
	public void removePaciente(Long id) {
		log.debug("removing paciente: " + id);
		pacientesDao.remove(id);
		
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
