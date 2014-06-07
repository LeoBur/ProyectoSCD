package com.bcpv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcpv.dao.SintomaDao;
import com.bcpv.model.Sintoma;
import com.bcpv.service.SintomaManager;
import com.bcpv.service.SintomaService;

@Service("sintomaManager")
@WebService(serviceName = "SintomaService", endpointInterface = "com.bcpv.service.SintomaService")
@Transactional
public class SintomaManagerImpl extends GenericManagerImpl<Sintoma, Long> implements SintomaManager, SintomaService{

	private SintomaDao sintomaDao;
	
	@Override
	@Autowired
	public void setSintomaDao(SintomaDao sintomaDao) {
		this.dao = sintomaDao;
		this.sintomaDao = sintomaDao;
		
	}
	
	@Override
	public Sintoma getSintoma(String idSintoma) {
		return sintomaDao.get(new Long (idSintoma));
	}
		
	@Override
	public Sintoma getSintoma(Long idSintoma) {
		return sintomaDao.get(idSintoma);
	}	
	
	@Override
	public List<Sintoma> getSintomas() {
		if (sintomaDao!=null){
			return sintomaDao.getSintomas();
		}
		return new ArrayList<Sintoma>();
	}
	
	@Override
	public Sintoma saveSintoma(final Sintoma sintoma)
			throws EntityExistsException {
		try {
			return sintomaDao.saveSintoma(sintoma);
		} catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("Sintoma ya existe");
		}
	}
	
	@Override
	public List<Sintoma> search(String searchTerm) {
		return super.search(searchTerm, Sintoma.class);
	}
	
	@Override
	public void removeSintoma(Sintoma sintoma) {
		log.debug("removing sintoma con id: " + sintoma.getIdSintoma());
        sintomaDao.remove(sintoma);
	}
	
	@Override
	public void removeSintoma(Long idSintoma) {
		log.debug("removing sintoma con id: " + idSintoma);
		sintomaDao.remove(idSintoma);
		
	}

	
}
