package com.bcpv.dao;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bcpv.model.Persona;

public interface PersonaDao extends UserDao{

    Persona savePersona(Persona persona);
    
    List<Persona> getPersonasByApellido(String apellido);

	Persona getPersonaByDni(Long dni);
    
}
