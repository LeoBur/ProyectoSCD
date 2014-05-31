package war.webapp.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import war.webapp.dao.ComidaDao;
import war.webapp.model.Comida;

public interface ComidaManager extends GenericManager<Comida, Long>{
	
	void setComidaDao(ComidaDao comidaDao);
	
	Comida getComida(Long idComida);
	
	Comida saveComida (Comida comida) throws EntityExistsException;
	
	void removeComida(Comida comida);
	
	void removeComida(String idComida);
	
	List<Comida> search(String searchTerm);

}
