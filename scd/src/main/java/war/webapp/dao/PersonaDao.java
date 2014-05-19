package war.webapp.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import war.webapp.model.Persona;


public interface PersonaDao extends GenericDao<Persona, Long>{
	
	Persona loadPersonaByDNI(Long dni) throws EntityNotFoundException;

    List<Persona> getPersonas();
    
    Persona savePersona(Persona persona);

}
