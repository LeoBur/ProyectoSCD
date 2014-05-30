package war.webapp.service;

import javax.persistence.EntityExistsException;

import war.webapp.dao.MomentoDiaDao;
import war.webapp.model.MomentoDia;

public interface MomentoDiaManager extends GenericManager<MomentoDia, Long>{
	
	void setMomentoDiaDao(MomentoDiaDao momentoDiaDao);
	
	MomentoDia getMomentoDia(Long idMomentoD);
	
	MomentoDia saveMomentoDia(MomentoDia momentoD) throws EntityExistsException;
	
	void removeMomentoDia(MomentoDia momentoD);
	
	void removeMomentoDia(String idMomentoD);

}
