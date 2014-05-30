package war.webapp.dao;

import javax.persistence.EntityNotFoundException;

import war.webapp.model.MomentoDia;

public interface MomentoDiaDao extends GenericDao<MomentoDia, Long>{
	
	MomentoDia loadMomentoDiaById(Long idMomentoD) throws EntityNotFoundException;
	
	MomentoDia saveMomentoD (MomentoDia momentoD);

}
