package com.bcpv.service.impl;

import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcpv.dao.PacienteEnTratamientoDao;
import com.bcpv.model.PacienteEnTratamiento;
import com.bcpv.model.Tratamiento;
import com.bcpv.service.PacienteEnTratamientoManager;
import com.bcpv.service.PacienteEnTratamientoService;

@Service("pacienteEnTratamientoManager")
@WebService(serviceName = "PacienteEnTratamientoService", endpointInterface = "com.bcpv.service.PacienteEnTratamientoService")
@Transactional
public class PacienteEnTratamientoManagerImpl extends GenericManagerImpl<PacienteEnTratamiento, Long> implements PacienteEnTratamientoManager, PacienteEnTratamientoService{

	private PacienteEnTratamientoDao pacienteEnTratamientoDao;

	@Override
	public PacienteEnTratamiento getPacienteEnTratamiento(String idPacienteEnTratamiento) {
		return pacienteEnTratamientoDao.get(new Long(idPacienteEnTratamiento));
	}

	@Override
	public void setPacienteEnTratamientoDao(PacienteEnTratamientoDao pacienteEnTratamientoDao) {
		this.dao=pacienteEnTratamientoDao;
		this.pacienteEnTratamientoDao=pacienteEnTratamientoDao;
		
	}

	@Override
	public PacienteEnTratamiento getPacienteEnTratamiento(Long idPacienteEnTratamiento) {
		return pacienteEnTratamientoDao.get(idPacienteEnTratamiento);
	}

	@Override
	public PacienteEnTratamiento savePacienteEnTratamiento(PacienteEnTratamiento pacienteEnTratamiento) {
		try{
			return pacienteEnTratamientoDao.save(pacienteEnTratamiento);
		}
		catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("PacienteEnTratamiento ya existe");
		}
	}

	@Override
	public void removePacienteEnTratamiento(PacienteEnTratamiento pacienteEnTratamiento) {
		log.debug("Removing pacienteEnTratamiento id: "+pacienteEnTratamiento.getIdPacienteEnTratamiento());
		pacienteEnTratamientoDao.remove(pacienteEnTratamiento);
		
	}

	@Override
	public void removePacienteEnTratamiento(Long idPacienteEnTratamiento) {
		log.debug("Removing pacienteEnTratamiento id: "+idPacienteEnTratamiento);
		pacienteEnTratamientoDao.remove(idPacienteEnTratamiento);
		
	}

	@Override
	public List<PacienteEnTratamiento> search(String searchTerm) {
		return super.search(searchTerm, Tratamiento.class);
	}
}
