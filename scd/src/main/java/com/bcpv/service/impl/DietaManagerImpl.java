package com.bcpv.service.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.bcpv.dao.DietaDao;
import com.bcpv.model.Dieta;
import com.bcpv.service.DietaManager;
import com.bcpv.service.DietaService;

@Service("dietaManager")
@WebService(serviceName = "DietaService", endpointInterface = "com.bcpv.service.DietaService")
public class DietaManagerImpl extends GenericManagerImpl<Dieta, Long> implements DietaManager,DietaService{
	
	private DietaDao dietaDao;

	@Override
	public void setDietaDao(DietaDao dietaDao) {
		// TODO Auto-generated method stub
		
	}
}
