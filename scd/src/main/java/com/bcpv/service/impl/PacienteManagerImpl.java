package com.bcpv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcpv.dao.PacienteDao;
import com.bcpv.model.Paciente;
import com.bcpv.model.Persona;
import com.bcpv.model.TipoDiabetes;
import com.bcpv.service.PacienteManager;
import com.bcpv.service.PacienteService;

@Service("pacienteManager")
@WebService(serviceName = "PacienteService", endpointInterface = "com.bcpv.service.PacienteService")
@Transactional
public class PacienteManagerImpl extends GenericManagerImpl<Paciente, Long> implements PacienteService, PacienteManager{

	private PacienteDao pacienteDao;
	
	@Override
	@Autowired
	public void setPacientesDao(PacienteDao pacienteDao) {
		this.dao = pacienteDao;
		this.pacienteDao = pacienteDao;
	}

	@Override
	public Paciente getPaciente(String id) {
		return pacienteDao.get(new Long(id));
	}

	@Override
	public Paciente getPaciente(Long dni) {
		return pacienteDao.getPaciente(dni);
	}

	@Override
	public List<Paciente> getPacientes() {
		if (pacienteDao!=null){
			return pacienteDao.getAllDistinct();
		}
		return new ArrayList<Paciente>();
	}

	@Override
	public Paciente savePaciente(final Paciente paciente)
			throws EntityExistsException {
		try {
			return pacienteDao.savePaciente(paciente);
		} catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("El paciente ya existe");
		}
	}

	@Override
	public List<Paciente> search(String searchTerm) {
		return super.search(searchTerm, Paciente.class);
	}

	@Override
	public void removePaciente(Paciente paciente) {
		log.debug("removing paciente: " + paciente.getPersona().getFirstName() + paciente.getPersona().getLastName());
		pacienteDao.remove(paciente);
	}

	@Override
	public void removePaciente(Long id) {
		log.debug("removing paciente: " + id);
		pacienteDao.remove(id);
		
	}
	
	@Override
	public List<Paciente> getPacientesByTipo(TipoDiabetes tipo) {
		return pacienteDao.loadPacientesByTipo(tipo);
	}

	@Override
	public List<Paciente> loadPacientesByApellido(List<Persona> persList) {
		return pacienteDao.loadPacientesByApellido(persList);
	}
	
	@Override
	public Paciente getPacienteByUsername(String username) {
		try {
            return pacienteDao.getPacienteByUsername(username);
        } catch (EntityNotFoundException e) {
            return null;
        }
	}
	
	@Override
	public Paciente loadPacienteByDNI(Persona persona) throws EntityNotFoundException{
		return pacienteDao.loadPacienteByDNI(persona);
	}
}
