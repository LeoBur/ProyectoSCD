package com.bcpv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcpv.dao.EspecialistaDao;
import com.bcpv.model.Especialista;
import com.bcpv.service.EspecialistaManager;
import com.bcpv.service.EspecialistaService;

@Service("especialistaManager")
@WebService(serviceName = "EspecialistaService", endpointInterface = "com.bcpv.service.EspecialistaService")
@Transactional
public class EspecialistaManagerImpl extends GenericManagerImpl<Especialista, Long> implements EspecialistaManager, EspecialistaService{

	private EspecialistaDao especialistaDao;
	
	@Override
	@Autowired
	public void setEspecialistaDao(EspecialistaDao especialistaDao) {
		this.dao = especialistaDao;
		this.especialistaDao = especialistaDao;
		
	}
	
	@Override
	public Especialista getEspecialista(String id) {
		return especialistaDao.get(new Long(id));
	}

	@Override
	public Especialista getEspecialista(Long id) {
		return especialistaDao.get(id);
	}

	@Override
	public List<Especialista> getEspecialistas() {
		if (especialistaDao!=null){
			return especialistaDao.getEspecialistas();
		}
		return new ArrayList<Especialista>();
	}

	@Override
	public Especialista saveEspecialista(final Especialista especialista)
			throws EntityExistsException {
		try {
			return especialistaDao.saveEspecialista(especialista);
		} catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("La Especialista ya existe");
		}
	}

	@Override
	public List<Especialista> search(String searchTerm) {
		return super.search(searchTerm, Especialista.class);
	}

	@Override
	public void removeEspecialista(Especialista especialista) {
		log.debug("removing Especialista: " + especialista.getNombre() + especialista.getApellido());
        especialistaDao.remove(especialista);
	}

	@Override
	public void removeEspecialista(Long id) {
		log.debug("removing Especialista: " + id);
		especialistaDao.remove(new Long(id));
		
	}
	
	@Override
	public List<Especialista> getEspecialistasByTipo(String tipo_esp) {
		if (especialistaDao!=null){
			return especialistaDao.getEspecialistaByTipo(tipo_esp);
		}
		return new ArrayList<Especialista>();
	}

}
