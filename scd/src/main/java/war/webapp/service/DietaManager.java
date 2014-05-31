package war.webapp.service;

import war.webapp.dao.DietaDao;
import war.webapp.model.Dieta;

public interface DietaManager extends GenericManager<Dieta, Long>{
	
	void setDietaDao(DietaDao dietaDao);

}
