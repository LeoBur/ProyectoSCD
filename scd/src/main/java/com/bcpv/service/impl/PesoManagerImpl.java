package com.bcpv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcpv.dao.PesoDao;
import com.bcpv.model.Peso;
import com.bcpv.service.PesoManager;
import com.bcpv.service.PesoService;

@Service("pesoManager")
@WebService(serviceName= "PesoService",endpointInterface = "com.bcpv.service.PesoService")
@Transactional
public class PesoManagerImpl extends GenericManagerImpl<Peso, Long> implements PesoService, PesoManager{

	private PesoDao pesoDao;
	
	@Override
	@Autowired
	public void setPesoDao(PesoDao pesoDao) {
		this.dao=pesoDao;
		this.pesoDao=pesoDao;
		
	}

	@Override
	public Peso getPeso(Long idPeso) {
		return pesoDao.get(idPeso);
	}

	@Override
	public void removePeso(String idPeso) {
		log.debug("Removing peso id: "+idPeso);
		pesoDao.remove(new Long(idPeso));
	}

	@Override
	public List<Peso> search(String searchTerm) {
		return super.search(searchTerm, Peso.class);
	}

	@Override
	public Peso getPeso(String id) {
		return pesoDao.get(new Long(id));
	}

	@Override
	public List<Peso> getPesos() {
		if(pesoDao !=null){
			return pesoDao.getAllDistinct();
		}
		return new ArrayList<Peso>();
	}

	@Override
	public Peso savePeso(Peso peso) throws EntityExistsException {
		try{
			return pesoDao.savePeso(peso);
		}
		catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("Peso ya existe");
			
		}
	}

	@Override
	public void removePeso(Peso peso) {
		log.debug("Removing peso id: "+peso.getId());
		pesoDao.remove(peso);
		
	}

}
