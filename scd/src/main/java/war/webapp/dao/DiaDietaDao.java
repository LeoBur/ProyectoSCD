package war.webapp.dao;

import javax.persistence.EntityNotFoundException;

import war.webapp.model.DiaDieta;

public interface DiaDietaDao extends GenericDao<DiaDieta, Long>{
	
	DiaDieta loadDiaDietaById(Long idDiaDieta) throws EntityNotFoundException;
	
	DiaDieta saveDiaDieta(DiaDieta diaDieta);
	
	// tal vez sea útil un método que devuelva todos los momentos del día de cada día

}
