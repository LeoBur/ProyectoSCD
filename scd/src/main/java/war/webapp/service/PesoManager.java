package war.webapp.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import war.webapp.dao.PesoDao;
import war.webapp.model.Peso;

public interface PesoManager extends GenericManager<Peso, Long>{

	void setPesoDao(PesoDao pesoDao);
	
	Peso getPeso(Long idPeso);
	
	List<Peso> getPesos();
	
	Peso savePeso(Peso peso) throws EntityExistsException;
	
	void removePeso(Peso peso);
	
	void removePeso(String idPeso);
	
	List<Peso> search(String searchTerm);
}
