package com.bcpv.service.impl;

import com.bcpv.dao.RegistroComidasDao;
import com.bcpv.model.RegistroComidas;
import com.bcpv.service.RegistroComidasManager;
import com.bcpv.service.RegistroComidasService;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;

@Service("registroComidasManager")
@WebService(serviceName = "RegistroComidasService", endpointInterface = "com.bcpv.service.RegistroComidasService")
public class RegistroComidasManagerImpl extends GenericManagerImpl<RegistroComidas, Long> implements RegistroComidasService, RegistroComidasManager {

	private RegistroComidasDao registroComidasDao;

	@Override
	public void setRegistroComidasDao(RegistroComidasDao registroComidasDao) {
		this.dao=registroComidasDao;
		this.registroComidasDao=registroComidasDao;
		
	}

	@Override
	public RegistroComidas getRegistroComidas(Long idRegComidas) {
		return registroComidasDao.get(idRegComidas);
	}

	@Override
	public void removeRegistroComidas(String idRegComidas) {
		log.debug("removing registroComidas id: "+idRegComidas);
		registroComidasDao.remove(new Long(idRegComidas));
		
	}

	@Override
	public List<RegistroComidas> search(String searchTerm) {
		return super.search(searchTerm, RegistroComidas.class);
	}

	@Override
	public RegistroComidas getRegistroComidas(String idRegistroComidas) {
		return registroComidasDao.get(new Long(idRegistroComidas));
	}

	@Override
	public List<RegistroComidas> getRegistrosComidas() {
		if (registroComidasDao!=null){
			return registroComidasDao.getAllDistinct();
		}
		return new ArrayList<RegistroComidas>();
	}

	@Override
	public RegistroComidas saveRegistroComidas(RegistroComidas registroComidas)
			throws EntityExistsException {
		try{
			return registroComidasDao.save(registroComidas);
		} 
		catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("RegistroComidas ya existe");
		}
	}

	@Override
	public void removeRegistroComidas(RegistroComidas registroComidas) {
		log.debug("removing registroComidas id: "+registroComidas.getIdRegistroComida());
		registroComidasDao.remove(registroComidas);
		
	}
}
