package com.bcpv.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bcpv.dao.PersonaDao;
import com.bcpv.model.Persona;
import com.bcpv.service.MailEngine;
import com.bcpv.service.PersonaManager;
import com.bcpv.service.PersonaService;
import com.bcpv.service.UserExistsException;

@Service("personaManager")
@WebService(serviceName = "PersonaService", endpointInterface = "com.bcpv.service.PersonaService")
public class PersonaManagerImpl extends UserManagerImpl implements PersonaManager,PersonaService{
   
    private PersonaDao personaDao;

    @Override
    @Autowired
    public void setPersonaDao(final PersonaDao personaDao) {
        this.dao = personaDao;
        this.personaDao = personaDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePersona(final String userId) {
        log.debug("removing user: " + userId);
        personaDao.remove(new Long(userId));
    }

    /**
     * {@inheritDoc}
     *
     * @param username the login name of the human
     * @return User the populated user object
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException thrown when username not found
     */
    @Override
    public Persona getPersonaByUsername(final String username) throws UsernameNotFoundException {
        return (Persona) personaDao.loadUserByUsername(username);
    }


	@Override
	public List<Persona> getPersonasByApellido(String apellido) {
		return personaDao.getPersonasByApellido(apellido);
	}

	@Override
	public Persona getPersonaByDni(Long dni) {
		return personaDao.getPersonaByDni(dni);
	}

	@Override
	public Persona getPersona(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Persona> getPersonas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Persona savePersona(Persona persona) throws UserExistsException {
		// TODO Auto-generated method stub
		return null;
	}
}
