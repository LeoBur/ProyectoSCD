package com.bcpv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import com.bcpv.dao.PrescripcionDao;
import com.bcpv.model.Prescripcion;
import com.bcpv.model.Tratamiento;
import com.bcpv.service.PrescripcionManager;
import com.bcpv.service.PrescripcionService;

public class PrescripcionManagerImpl extends GenericManagerImpl<Prescripcion, Long> implements PrescripcionManager, PrescripcionService{

	private PrescripcionDao prescripcionDao;

	@Override
	public Prescripcion getPrescripcion(String idPrescripcion) {
		return prescripcionDao.get(new Long(idPrescripcion));
	}

	@Override
	public void setPrescripcionDao(PrescripcionDao prescripcionDao) {
		this.dao=prescripcionDao;
		this.prescripcionDao=prescripcionDao;
		
	}

	@Override
	public Prescripcion getPrescripcion(Long idPrescripcion) {
		return prescripcionDao.get(idPrescripcion);
	}

	@Override
	public List<Prescripcion> getPrescripcionesByIdTratamiento(
			Long idTratamiento) {
		if (prescripcionDao!=null){
			return prescripcionDao.getPrescripcionesByIdTratamiento(idTratamiento);
		}
		return new ArrayList<Prescripcion>();
	}

	@Override
	public Prescripcion savePrescripcion(Prescripcion prescripcion) {
		try{
			return prescripcionDao.save(prescripcion);
		}
		catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("Prescripcion ya existe");
		}
	}

	@Override
	public void removePrescripcion(Prescripcion prescripcion) {
		log.debug("Removing prescripcion id: "+prescripcion.getIdPrescripcion());
		prescripcionDao.remove(prescripcion);
		
	}

	@Override
	public void removePrescripcion(Long idPrescripcion) {
		log.debug("Removing prescripcion id: "+idPrescripcion);
		prescripcionDao.remove(idPrescripcion);
		
	}

	@Override
	public List<Prescripcion> search(String searchTerm) {
		return super.search(searchTerm, Tratamiento.class);
	}
	
}
