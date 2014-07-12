package com.bcpv.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.bcpv.model.Persona;
import com.bcpv.model.User;

@WebService
@Path("/personas")
public interface PersonaService extends UserService{
	   /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    @GET
    @Path("{id}")
    Persona getPersona(@PathParam("id") String userId);

    /**
     * Finds a user by their username.
     *
     * @param username the user's username used to login
     * @return Persona a populated user object
     */
    Persona getPersonaByUsername(@PathParam("username") String username);

    /**
     * Retrieves a list of all users.
     *
     * @return List
     */
    @GET
    List<Persona> getPersonas();

    /**
     * Saves a user's information
     *
     * @param user the user's information
     * @return updated user
     * @throws UserExistsException thrown when user already exists
     */
    @POST
    Persona savePersona(Persona persona) throws UserExistsException;

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    @DELETE
    void removePersona(String userId);
}


