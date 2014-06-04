package com.bcpv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcpv.dao.ProvinciaDao;
import com.bcpv.model.Provincia;
import com.bcpv.service.ProvinciaManager;
import com.bcpv.service.ProvinciaService;

@Service("provinciaManager")
@WebService(serviceName = "ProvinciaService", endpointInterface = "com.bcpv.service.ProvinciaService")
public class ProvinciaManagerImpl extends GenericManagerImpl<Provincia, Long> implements ProvinciaService, ProvinciaManager{
	
	private ProvinciaDao provinciaDao;
	
	@Override
	@Autowired
	public void setProvinciaDao(ProvinciaDao provinciaDao) {
		this.dao = provinciaDao;
		this.provinciaDao = provinciaDao;
		
	}
	
	@Override
	public Provincia getProvincia(String id_provincia) {
		return provinciaDao.get(new Long(id_provincia));
	}

	@Override
	public Provincia getProvincia(Long id_provincia) {
		return provinciaDao.get(id_provincia);
	}

	@Override
	public List<Provincia> getProvincias() {
		if (provinciaDao!=null){
			return provinciaDao.getAllDistinct();
		}
		return new ArrayList<Provincia>();
	}

	@Override
	public Provincia saveProvincia(final Provincia provincia)
			throws EntityExistsException {
		try {
			return provinciaDao.saveProvincia(provincia);
		} catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("La provincia ya existe");
		}
	}

	@Override
	public List<Provincia> search(String searchTerm) {
		return super.search(searchTerm, Provincia.class);
	}

	@Override
	public void removeProvincia(Provincia provincia) {
		log.debug("removing provincia: " + provincia.getNombre());
        provinciaDao.remove(provincia);
	}

	@Override
	public void removeProvincia(String id_provincia) {
		log.debug("removing provincia: " + id_provincia);
		provinciaDao.remove(new Long(id_provincia));
		
	}

}
