package com.bcpv.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bcpv.dao.PersonaDao;
import com.bcpv.dao.UserDao;
import com.bcpv.model.Persona;
import com.bcpv.model.User;

public interface PersonaManager extends UserManager{
	/**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param personaDao the PersonaDao implementation to use
     */
    void setPersonaDao(PersonaDao personaDao);
   
    List<Persona> getPersonasByApellido(String apellido);
    
    Persona getPersonaByDni(Long dni);
    
    Persona savePersona(Persona persona) throws EntityExistsException;
}
