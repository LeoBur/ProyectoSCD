package war.webapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;

import war.webapp.dao.PersonaDao;
import war.webapp.model.Persona;
import war.webapp.service.PersonaManager;
import war.webapp.service.PersonaService;

@Service("personaManager")
@WebService(serviceName = "PersonaService", endpointInterface = "war.webapp.service.PersonaService")
public class PersonaManagerImpl extends GenericManagerImpl<Persona, Long> implements PersonaService, PersonaManager{
	
private PersonaDao personaDao;
	
	@Override
	public void setPersonaDao(PersonaDao personaDao) {
		this.dao = personaDao;
		this.personaDao = personaDao;
		
	}
	
	@Override
	public Persona getPersona(String id_persona) {
		return personaDao.get(new Long(id_persona));
	}

	@Override
	public Persona getPersona(Long id_persona) {
		return personaDao.get(id_persona);
	}

	@Override
	public List<Persona> getPersonas() {
		if (personaDao!=null){
			return personaDao.getAllDistinct();
		}
		return new ArrayList<Persona>();
	}

	@Override
	public Persona savePersona(final Persona persona)
			throws EntityExistsException {
		try {
			return personaDao.savePersona(persona);
		} catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("La persona ya existe");
		}
	}

	@Override
	public List<Persona> search(String searchTerm) {
		return super.search(searchTerm, Persona.class);
	}

	@Override
	public void removePersona(Persona persona) {
		log.debug("removing persona: " + persona.getNombre() + persona.getApellido());
        personaDao.remove(persona);
	}

	@Override
	public void removePersona(String id_persona) {
		log.debug("removing persona: " + id_persona);
		personaDao.remove(new Long(id_persona));
		
	}

}
