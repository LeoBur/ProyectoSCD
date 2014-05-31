package war.webapp.service.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import war.webapp.dao.DietaDao;
import war.webapp.model.Dieta;
import war.webapp.service.DietaManager;
import war.webapp.service.DietaService;

@Service("dietaManager")
@WebService(serviceName = "DietaService", endpointInterface = "war.webapp.service.DietaService")
public class DietaManagerImpl extends GenericManagerImpl<Dieta, Long> implements DietaManager,DietaService{
	
	private DietaDao dietaDao;

	@Override
	public void setDietaDao(DietaDao dietaDao) {
		// TODO Auto-generated method stub
		
	}
}
