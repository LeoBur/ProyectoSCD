package war.webapp.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import war.webapp.model.Sintoma;

public interface SintomaDao extends GenericDao<Sintoma, Long> {
	
	Sintoma loadSintomaById(Long idSintoma) throws EntityNotFoundException;
	
	List<Sintoma> getSintomas();
	
	Sintoma saveSintoma (Sintoma sintoma);
}
