package com.bcpv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;

import com.bcpv.dao.LocalidadDao;
import com.bcpv.model.Localidad;
import com.bcpv.service.LocalidadManager;
import com.bcpv.service.LocalidadService;

@Service("localidadManager")
@WebService(serviceName = "LocalidadService", endpointInterface = "com.bcpv.webapp.service.LocalidadService")
public class LocalidadManagerImpl extends GenericManagerImpl<Localidad, Long> implements LocalidadService, LocalidadManager{

	private LocalidadDao localidadDao;
	
	@Override
	public void setLocalidadDao(LocalidadDao localidadDao) {
		this.dao = localidadDao;
		this.localidadDao = localidadDao;
		
	}
	
	@Override
	public Localidad getLocalidad(String id) {
		return localidadDao.get(new Long(id));
	}

	@Override
	public Localidad getLocalidad(Long id) {
		return localidadDao.get(id);
	}

	@Override
	public List<Localidad> getLocalidades() {
		if (localidadDao!=null){
			return localidadDao.getAllDistinct();
		}
		return new ArrayList<Localidad>();
	}

	@Override
	public Localidad saveLocalidad(final Localidad localidad)
			throws EntityExistsException {
		try {
			return localidadDao.saveLocalidad(localidad);
		} catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("La Localidad ya existe");
		}
	}

	@Override
	public List<Localidad> search(String searchTerm) {
		return super.search(searchTerm, Localidad.class);
	}

	@Override
	public void removeLocalidad(Localidad localidad) {
		log.debug("removing localidad: " + localidad.getNombre());
        localidadDao.remove(localidad);
	}

	@Override
	public void removeLocalidad(String id) {
		log.debug("removing persona: " + id);
		localidadDao.remove(new Long(id));
		
	}
}
