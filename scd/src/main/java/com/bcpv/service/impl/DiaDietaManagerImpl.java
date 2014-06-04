package com.bcpv.service.impl;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcpv.dao.DiaDietaDao;
import com.bcpv.model.DiaDieta;
import com.bcpv.service.DiaDietaManager;
import com.bcpv.service.DiaDietaService;

@Service("diaDietaManager")
@WebService(serviceName = "DiaDietaService", endpointInterface = "com.bcpv.service.DiaDietaService")
public class DiaDietaManagerImpl extends GenericManagerImpl<DiaDieta, Long> implements DiaDietaManager, DiaDietaService{

	private DiaDietaDao diaDietaDao;
	
	@Override
	@Autowired
	public void setDiaDietaDao(DiaDietaDao diaDietaDao) {
		this.dao=diaDietaDao;
		this.diaDietaDao=diaDietaDao;
		
	}
	
	@Override
	public DiaDieta getDiaDieta(String idDiaDieta) {
		return diaDietaDao.get(new Long(idDiaDieta));
	}

	@Override
	public void removeDiaDieta(DiaDieta diaDieta) {
		log.debug("removing diaDieta id: "+diaDieta.getIdDiaDieta());
		diaDietaDao.remove(diaDieta);
		
	}

	@Override
	public DiaDieta getDiaDieta(Long idDiaDieta) {
		return diaDietaDao.get(idDiaDieta);
	}

	@Override
	public DiaDieta saveDiaDieta(DiaDieta diaDietaD)
			throws EntityExistsException {
		try{
			return diaDietaDao.save(diaDietaD);
		}
		catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("DiaDieta ya existe");
		}
	}


	@Override
	public void removeDiaDieta(String idDiaDieta) {
		log.debug("removing DiaDieta id: "+idDiaDieta);
		diaDietaDao.remove(new Long(idDiaDieta));
		
	}

}
