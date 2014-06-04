package com.bcpv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcpv.dao.MedicionDao;
import com.bcpv.model.Medicion;
import com.bcpv.service.MedicionManager;
import com.bcpv.service.MedicionService;

@Service("medicionManager")
@WebService(serviceName = "MedicionService", endpointInterface = "com.bcpv.service.MedicionService")
public class MedicionManagerImpl extends GenericManagerImpl<Medicion, Long> implements MedicionManager, MedicionService {

	private MedicionDao medicionDao;
	
	@Override
	@Autowired
	public void setMedicionDao(MedicionDao medicionDao) {
		this.dao = medicionDao;
		this.medicionDao = medicionDao;
		
	}
	
	@Override
	public Medicion getMedicion(String id_medicion) {
		return medicionDao.get(new Long(id_medicion));
	}

	@Override
	public Medicion getMedicion(Long id_medicion) {
		return medicionDao.get(id_medicion);
	}

	@Override
	public List<Medicion> getMediciones() {
		if (medicionDao!=null){
			return medicionDao.getAllDistinct();
		}
		return new ArrayList<Medicion>();
	}

	@Override
	public Medicion saveMedicion(final Medicion medicion)
			throws EntityExistsException {
		try {
			return medicionDao.saveMedicion(medicion);
		} catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("Medición ya existe");
		}
	}

	@Override
	public List<Medicion> search(String searchTerm) {
		return super.search(searchTerm, Medicion.class);
	}

	@Override
	public void removeMedicion(Medicion medicion) {
		log.debug("removing medicion: " + medicion.getId_medicion());
        medicionDao.remove(medicion);
	}

	@Override
	public void removeMedicion(String id_medicion) {
		log.debug("removing medicion: " + id_medicion);
		medicionDao.remove(new Long(id_medicion));
		
	}

}
