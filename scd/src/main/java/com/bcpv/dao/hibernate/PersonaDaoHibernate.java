package com.bcpv.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.bcpv.dao.PersonaDao;
import com.bcpv.model.Persona;

public class PersonaDaoHibernate extends UserDaoHibernate implements PersonaDao, UserDetailsService{


    /**
     * {@inheritDoc}
     */
    public Persona savePersona(Persona persona) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + persona.getId());
        }
        getSession().saveOrUpdate(persona);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return persona;
    }

    /**
     * Overridden simply to call the saveUser method. This is happening
     * because saveUser flushes the session and saveObject of BaseDaoHibernate
     * does not.
     *
     * @param user the user to save
     * @return the modified user (with a primary key set if they're new)
     */
    @Override
    public Persona save(Persona persona) {
        return this.savePersona(persona);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> getPersonasByApellido(String apellido) {
		List<Persona> persList = getSession().createCriteria(Persona.class).add(Restrictions.eq("lastName", apellido)).list();
        if (persList == null || persList.isEmpty()) {
            throw new EntityNotFoundException("No existen personas con el apellido " + apellido);
        } else {
            return persList;
        }
	}

	@Override
	public Persona getPersonaByDni(Long dni) {
		//Persona persona = (Persona) getSession().createCriteria(Persona.class).add(Restrictions.eq("dni", dni));
		Query qry = getSession().createQuery("from Persona p where p.dni = dni");
		if qry.
		if (persona == null) {
            //throw new EntityNotFoundException("No existen personas con el dni" + dni);
			return new Persona();
        } else {
            return persona;
        }
	}

}
