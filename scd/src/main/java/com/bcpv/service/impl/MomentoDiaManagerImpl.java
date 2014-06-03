package com.bcpv.service.impl;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;

import com.bcpv.dao.MomentoDiaDao;
import com.bcpv.model.MomentoDia;
import com.bcpv.service.MomentoDiaManager;
import com.bcpv.service.MomentoDiaService;

@Service("momentoDiaManager")
@WebService(serviceName="MomentoDiaService", endpointInterface="com.bcpv.webapp.service.MomentoDiaService")
public class MomentoDiaManagerImpl extends GenericManagerImpl<MomentoDia, Long> implements MomentoDiaManager, MomentoDiaService{

	private MomentoDiaDao momentoDiaDao;
	
	@Override
	public MomentoDia getMomentoDia(String idMomentoD) {
		return momentoDiaDao.get(new Long(idMomentoD));
	}

	@Override
	public void setMomentoDiaDao(MomentoDiaDao momentoDiaDao) {
		this.dao=momentoDiaDao;
		this.momentoDiaDao=momentoDiaDao;
	}

	@Override
	public MomentoDia getMomentoDia(Long idMomentoD) {
		return momentoDiaDao.get(idMomentoD);
	}

	@Override
	public MomentoDia saveMomentoDia(MomentoDia momentoD)
			throws EntityExistsException {
		try{
			return momentoDiaDao.saveMomentoD(momentoD);
		}
		catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("MomentoDia ya existe");
		}
	}

	@Override
	public void removeMomentoDia(MomentoDia momentoD) {
		log.debug("removing momentoDia id: "+momentoD.getIdMomentoD());
		momentoDiaDao.remove(momentoD);
		
	}

	@Override
	public void removeMomentoDia(String idMomentoD) {
		log.debug("removing momentoDia id: "+idMomentoD);
		momentoDiaDao.remove(new Long(idMomentoD));
		
	}

}
