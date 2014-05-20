package war.webapp.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import war.webapp.dao.SintomaDao;
import war.webapp.model.Sintoma;

public interface SintomaManager extends GenericManager<Sintoma, Long> {
	
	void setSintomaDao(SintomaDao sintomaDao);
	
	Sintoma getSintoma(Long idSintoma);
	
	List<Sintoma> getSintomas();
	
	Sintoma saveSintoma(Sintoma sintoma) throws EntityExistsException;
	
	void removeSintoma(Sintoma sintoma);
	
	void removeSintoma(Long idSintoma);
	
	List<Sintoma> search(String searchTerm);

}
