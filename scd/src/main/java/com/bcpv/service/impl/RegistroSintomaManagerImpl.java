package com.bcpv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;

import com.bcpv.dao.RegistroSintomaDao;
import com.bcpv.model.RegistroSintoma;
import com.bcpv.service.RegistroSintomaManager;
import com.bcpv.service.RegistroSintomaService;

@Service("registroSintomaManager")
@WebService(serviceName = "RegistroSintomaService", endpointInterface = "com.bcpv.service.RegistroSintomaService")
public class RegistroSintomaManagerImpl extends GenericManagerImpl<RegistroSintoma, Long> implements RegistroSintomaManager,RegistroSintomaService{

	private RegistroSintomaDao registroSintomaDao;

	@Override
	public void setRegistroSintomaDao(RegistroSintomaDao registroSintomaDao) {
		this.dao=registroSintomaDao;
		this.registroSintomaDao=registroSintomaDao;
		
	}

	@Override
	public RegistroSintoma getRegistroSintoma(Long idRegSintoma) {
		return registroSintomaDao.get(idRegSintoma);
	}

	@Override
	public void removeRegistroSintoma(String idRegSintoma) {
		log.debug("removing registroSintoma id: "+idRegSintoma);
		registroSintomaDao.remove(new Long(idRegSintoma));
		
	}

	@Override
	public List<RegistroSintoma> search(String searchTerm) {
		return super.search(searchTerm, RegistroSintoma.class);
	}

	@Override
	public RegistroSintoma getRegistroSintoma(String idRegistroSintoma) {
		return registroSintomaDao.get(new Long(idRegistroSintoma));
	}

	@Override
	public List<RegistroSintoma> getRegistrosSintomas() {
		if (registroSintomaDao!=null){
			return registroSintomaDao.getAllDistinct();
		}
		return new ArrayList<RegistroSintoma>();
	}

	@Override
	public RegistroSintoma saveRegistroSintoma(RegistroSintoma registroSintoma)
			throws EntityExistsException {
		try{
			return registroSintomaDao.save(registroSintoma);
		} 
		catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("RegistroSintoma ya existe");
		}
	}

	@Override
	public void removeRegistroSintoma(RegistroSintoma registroSintoma) {
		log.debug("removing registroSintoma id: "+registroSintoma.getIdRegSintoma());
		registroSintomaDao.remove(registroSintoma);
		
	}
}
