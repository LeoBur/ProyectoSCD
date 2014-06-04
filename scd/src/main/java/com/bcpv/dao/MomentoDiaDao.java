package com.bcpv.dao;

import javax.persistence.EntityNotFoundException;

import com.bcpv.model.MomentoDia;

public interface MomentoDiaDao extends GenericDao<MomentoDia, Long>{
	
	MomentoDia loadMomentoDiaById(Long idMomentoD) throws EntityNotFoundException;
	
	MomentoDia saveMomentoD (MomentoDia momentoD);

}
