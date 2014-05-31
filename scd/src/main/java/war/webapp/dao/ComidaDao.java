package war.webapp.dao;

import javax.persistence.EntityNotFoundException;

import war.webapp.model.Comida;

public interface ComidaDao extends GenericDao<Comida, Long> {
	
	Comida loadComidaById(Long idComida) throws EntityNotFoundException;
	
	Comida saveComida(Comida comida);
	

}
