package com.bcpv.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bcpv.dao.PersonaDao;
import com.bcpv.model.Persona;
import com.bcpv.service.PersonaManager;
import com.bcpv.service.PersonaService;

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

	/*	@Override
	    public Persona savePersona(final Persona user) throws EntityExistsException {

	        if (user.getVersion() == null) {
	            // if new user, lowercase userId
	            user.setUsername(user.getUsername().toLowerCase());
	        }

	        // Get and prepare password management-related artifacts
	        boolean passwordChanged = false;
	        if (passwordEncoder != null) {
	            // Check whether we have to encrypt (or re-encrypt) the password
	            if (user.getVersion() == null) {
	                // New user, always encrypt
	                passwordChanged = true;
	            } else {
	                // Existing user, check password in DB
	                final String currentPassword = personaDao.getUserPassword(user.getId());
	                if (currentPassword == null) {
	                    passwordChanged = true;
	                } else {
	                    if (!currentPassword.equals(user.getPassword())) {
	                        passwordChanged = true;
	                    }
	                }
	            }

	            // If password was changed (or new user), encrypt it
	            if (passwordChanged) {
	                user.setPassword(passwordEncoder.encode(user.getPassword()));
	            }
	        } else {
	            log.warn("PasswordEncoder not set, skipping password encryption...");
	        }

	        try {
	            return personaDao.savePersona(user);
	        } catch (final Exception e) {
	            e.printStackTrace();
	            log.warn(e.getMessage());
	            throw new UserExistsException("Persona '" + user.getUsername() + "' already exists!");
	        }

	}*/
}
