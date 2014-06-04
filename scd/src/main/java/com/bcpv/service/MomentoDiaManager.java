package com.bcpv.service;

import javax.persistence.EntityExistsException;

import com.bcpv.dao.MomentoDiaDao;
import com.bcpv.model.MomentoDia;

public interface MomentoDiaManager extends GenericManager<MomentoDia, Long>{
	
	void setMomentoDiaDao(MomentoDiaDao momentoDiaDao);
	
	MomentoDia getMomentoDia(Long idMomentoD);
	
	MomentoDia saveMomentoDia(MomentoDia momentoD) throws EntityExistsException;
	
	void removeMomentoDia(MomentoDia momentoD);
	
	void removeMomentoDia(String idMomentoD);

}
