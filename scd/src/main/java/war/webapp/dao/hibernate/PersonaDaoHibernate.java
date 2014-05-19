package war.webapp.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import war.webapp.dao.PersonaDao;
import war.webapp.model.Persona;

public abstract class PersonaDaoHibernate extends GenericDaoHibernate<Persona, Long> implements PersonaDao{
	
	public PersonaDaoHibernate() {
        super(Persona.class);
    }
	
	@Override
	public Persona loadPersonaByDNI(Long dni) throws EntityNotFoundException {
		Persona persona = (Persona) getSession().createCriteria(Persona.class).add(Restrictions.eq("dni_persona", dni));
		if (persona == null){
			throw new EntityNotFoundException("La persona con DNI :" + dni + " no existe");
		} else {
			return persona;
		}
	}
	
    @SuppressWarnings("unchecked")
    public List<Persona> getPersonas() {
		Query qry = getSession().createQuery("from Personas p order by upper(p.apellido_persona)");
        return qry.list();
	}

    @Override
    public Persona savePersona(Persona persona) {
    	if (log.isDebugEnabled()) {
            log.debug("Persona id: " + persona.getId_persona());
        }
        getSession().saveOrUpdate(persona);
        // necessary to throw a DataIntegrityViolation and catch it in MedicionManager
        getSession().flush();
        return persona;
    }

}
