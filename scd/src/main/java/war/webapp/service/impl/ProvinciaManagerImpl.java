package war.webapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import war.webapp.dao.ProvinciaDao;
import war.webapp.model.Provincia;
import war.webapp.service.ProvinciaManager;
import war.webapp.service.ProvinciaService;

public class ProvinciaManagerImpl extends GenericManagerImpl<Provincia, Long> implements ProvinciaService, ProvinciaManager{
	
	private ProvinciaDao provinciaDao;
	
	@Override
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
