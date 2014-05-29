package war.webapp.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import war.webapp.model.Especialista;

public interface EspecialistaDao extends GenericDao<Especialista, Long>{
	
	Especialista loadEspecialistaByDNI(Long dni) throws EntityNotFoundException;

    List<Especialista> getEspecialistas();
    
    Especialista saveEspecialista(Especialista especialista);
	
	List<Especialista> getEspecialistaByTipo (String tipo);

}
