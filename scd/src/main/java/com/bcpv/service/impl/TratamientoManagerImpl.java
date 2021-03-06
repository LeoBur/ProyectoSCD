package com.bcpv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcpv.dao.TratamientoDao;
import com.bcpv.model.Tratamiento;
import com.bcpv.service.TratamientoManager;
import com.bcpv.service.TratamientoService;

@Service("tratamientoManager")
@WebService(serviceName="TratamientoManager", endpointInterface = "com.bcpv.service.TratamientoService")
@Transactional
public class TratamientoManagerImpl extends GenericManagerImpl<Tratamiento, Long> implements TratamientoManager, TratamientoService{

	private TratamientoDao tratamientoDao;
	
	@Override
	@Autowired
	public void setTratamientoDao(TratamientoDao tratamientoDao){
		this.dao=tratamientoDao;
		this.tratamientoDao=tratamientoDao;
	}
	
	@Override
	public Tratamiento getTratamiento(String idTratamiento) {
		return tratamientoDao.get(new Long(idTratamiento));
	}

	@Override
	public List<Tratamiento> getTratamientosByIdPaciente(Long idPaciente) {
		if (tratamientoDao!=null){
			return tratamientoDao.getTratamientosByIdPaciente(idPaciente);
		}
		return new ArrayList<Tratamiento>();
	}

	@Override
	public Tratamiento getUltimoTratamientoByIdPaciente(Long idPaciente) {
		return tratamientoDao.getUltimoTratamientoByIdPaciente(idPaciente);
	}

	
	@Override
	public Tratamiento getTratamiento(Long idTratamiento) {
		return tratamientoDao.get(idTratamiento);
	}

	@Override
	public Tratamiento saveTratamiento(Tratamiento tratamiento)
			throws EntityExistsException {
		try{
			return tratamientoDao.save(tratamiento);
		}
		catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("Tratamiento ya existe");
		}
	}

	@Override
	public void removeTratamiento(Tratamiento tratamiento) {
		log.debug("Removing tratamiento id: "+tratamiento.getIdTratamiento());
		tratamientoDao.remove(tratamiento);
		
	}

	@Override
	public void removeTratamiento(Long idTratamiento) {
		log.debug("Removing tratamiento id: "+idTratamiento);
		tratamientoDao.remove(idTratamiento);
		
	}

	@Override
	public List<Tratamiento> search(String searchTerm) {
		return super.search(searchTerm, Tratamiento.class);
	}

}
