package war.webapp.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import war.webapp.dao.PersonaDao;
import war.webapp.model.Persona;

public interface PersonaManager extends GenericManager<Persona, Long>{
	
	void setEspecialistaDao(PersonaDao personaDao);

	   
    Persona getPersona(Long id_persona);

    List<Persona> getPersonas();

    Persona savePersona(Persona persona) throws EntityExistsException;
    
    void removePersona(Persona persona);
    
    void removePersona(String id_persona);

    List<Persona> search(String searchTerm);

}
