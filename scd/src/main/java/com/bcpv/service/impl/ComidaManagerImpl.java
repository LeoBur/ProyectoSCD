package com.bcpv.service.impl;

import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcpv.dao.ComidaDao;
import com.bcpv.model.Comida;
import com.bcpv.service.ComidaManager;
import com.bcpv.service.ComidaService;

@Service("comidaManager")
@WebService(serviceName="ComidaService", endpointInterface="com.bcpv.service.ComidaService")
@Transactional
public class ComidaManagerImpl extends GenericManagerImpl<Comida, Long> implements ComidaManager, ComidaService{

	private ComidaDao comidaDao;
	
	@Override
	@Autowired
	public void setComidaDao(ComidaDao comidaDao) {
		this.dao=comidaDao;
		this.comidaDao=comidaDao;
		
	}
	
	@Override
	public Comida getComida(String idComida) {
		return comidaDao.get(new Long (idComida));
	}

	@Override
	public Comida getComida(Long idComida) {
		return comidaDao.get(idComida);
	}

	@Override
	public Comida saveComida(Comida comida) throws EntityExistsException {
		try{
			return comidaDao.save(comida);
		}
		catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("Comida ya existe");
		}
	}

	@Override
	public void removeComida(Comida comida) {
		log.debug("removing comida id: "+comida.getIdComida());
		comidaDao.remove(comida);
		
	}

	@Override
	public void removeComida(String idComida) {
		log.debug("removing comida id: "+idComida);
		comidaDao.remove(new Long(idComida));
		
	}

	@Override
	public List<Comida> search(String searchTerm) {
		return super.search(searchTerm, Comida.class);
	}

}
