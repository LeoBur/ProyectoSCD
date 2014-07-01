package com.bcpv.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bcpv.dao.PersonaDao;
import com.bcpv.model.Paciente;
import com.bcpv.model.Persona;

public class PersonaDaoHibernate extends GenericDaoHibernate<Persona, Long> implements PersonaDao, UserDetailsService{
	/**
     * Constructor that sets the entity to User.class.
     */
    public PersonaDaoHibernate() {
        super(Persona.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Persona> getPersonas() {
        Query qry = getSession().createQuery("from Persona u order by upper(u.username)");
        return qry.list();
    }

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

    /**
     * {@inheritDoc}
    */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List personas = getSession().createCriteria(Persona.class).add(Restrictions.eq("username", username)).list();
        if (personas == null || personas.isEmpty()) {
            throw new UsernameNotFoundException("user '" + username + "' not found...");
        } else {
            return (UserDetails) personas.get(0);
        }
    }

    /**
     * {@inheritDoc}
    */
    public String getUserPassword(Long userId) {
        JdbcTemplate jdbcTemplate =
                new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
        Table table = AnnotationUtils.findAnnotation(Persona.class, Table.class);
        return jdbcTemplate.queryForObject(
                "select password from " + table.name() + " where id=?", String.class, userId);
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
		Persona persona = (Persona) getSession().createCriteria(Persona.class).add(Restrictions.eq("dni", dni));
		if (persona == null) {
            throw new EntityNotFoundException("No existen personas con el dni" + dni);
        } else {
            return persona;
        }
	}

}
