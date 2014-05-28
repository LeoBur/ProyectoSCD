package war.webapp.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import war.webapp.model.Peso;

public interface PesoDao extends GenericDao<Peso, Long>{
	
	Peso loadPesoById(Long id) throws EntityNotFoundException;
	
	List<Peso> getPesos();
	
	Peso savePeso(Peso peso);
}
