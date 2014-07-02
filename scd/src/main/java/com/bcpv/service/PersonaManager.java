package com.bcpv.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bcpv.dao.PersonaDao;
import com.bcpv.dao.UserDao;
import com.bcpv.model.Persona;
import com.bcpv.model.User;

public interface PersonaManager extends GenericManager<Persona, Long>{
	/**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param personaDao the PersonaDao implementation to use
     */
    void setPersonaDao(PersonaDao personaDao);

    /**
     * Convenience method for testing - allows you to mock the PasswordEncoder and set it on an interface.
     * @param passwordEncoder the PasswordEncoder implementation to use
     */
    void setPasswordEncoder(PasswordEncoder passwordEncoder);


    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return Persona
     */
    Persona getPersona(String userId);

    /**
     * Finds a user by their username.
     * @param username the user's username used to login
     * @return Persona a populated user object
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException
     *         exception thrown when user not found
     */
    Persona getPersonaByUsername(String username) throws UsernameNotFoundException;

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Persona> getPersonas();

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Persona savePersona(Persona persona) throws UserExistsException;

    /**
     * Removes a persona from the database
     *
     * @param persona the persona to remove
     */
    void removePersona(Persona persona);

    /**
     * Removes a persona from the database by their userId
     *
     * @param userId the user's id
     */
    void removePersona(String userId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Persona> search(String searchTerm);

    /**
     * Builds a recovery password url by replacing placeholders with username and generated recovery token.
     * 
     * UrlTemplate should include two placeholders '{username}' for username and '{token}' for the recovery token.
     * 
     * @param user
     * @param urlTemplateurl
     *            template including two placeholders '{username}' and '{token}'
     * @return
     */
    String buildRecoveryPasswordUrl(Persona persona, String urlTemplate);

    /**
     *
     * @param user
     * @return
     */
    String generateRecoveryToken(Persona persona);

    /**
     *
     * @param username
     * @param token
     * @return
     */
    boolean isRecoveryTokenValid(String username, String token);

    /**
     * 
     * @param user
     * @param token
     * @return
     */
    boolean isRecoveryTokenValid(Persona user, String token);

    /**
     * Sends a password recovery email to username.
     *
     * @param username
     * @param urlTemplate
     *            url template including two placeholders '{username}' and '{token}'
     */
    void sendPasswordRecoveryEmail(String username, String urlTemplate);

    /**
     * 
     * @param username
     * @param currentPassword
     * @param recoveryToken
     * @param newPassword
     * @param applicationUrl
     * @return
     * @throws UserExistsException
     */
    Persona updatePassword(String username, String currentPassword, String recoveryToken, String newPassword, String applicationUrl) throws UserExistsException;
    
    List<Persona> getPersonasByApellido(String apellido);
    
    Persona getPersonaByDni(Long dni);
}
