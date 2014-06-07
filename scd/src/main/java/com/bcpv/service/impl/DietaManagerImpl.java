package com.bcpv.service.impl;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcpv.dao.DietaDao;
import com.bcpv.model.Dieta;
import com.bcpv.service.DietaManager;
import com.bcpv.service.DietaService;

@Service("dietaManager")
@WebService(serviceName = "DietaService", endpointInterface = "com.bcpv.service.DietaService")
@Transactional
public class DietaManagerImpl extends GenericManagerImpl<Dieta, Long> implements DietaManager,DietaService{
	
	private DietaDao dietaDao;

	@Override
	public void setDietaDao(DietaDao dietaDao) {
		this.dao=dietaDao;
		this.dietaDao=dietaDao;
		
	}

	@Override
	public Dieta getDieta(String idDieta) {
		return dietaDao.get(new Long(idDieta));
	}

	@Override
	public Dieta getDieta(Long idDieta) {
		return dietaDao.get(idDieta);
	}

	@Override
	public Dieta saveDieta(Dieta dieta) throws EntityExistsException {
		try{
			return dietaDao.saveDieta(dieta);
		} 
		catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("Dieta ya existe");
		}
	}

	@Override
	public void removeDieta(Dieta dieta) {
		log.debug("removing diaDieta id: "+dieta.getIdDieta());
		dietaDao.remove(dieta);
		
	}

	@Override
	public void removeDieta(String idDieta) {
		log.debug("removing diaDieta id: "+idDieta);
		dietaDao.remove(new Long(idDieta));
		
	}
}
