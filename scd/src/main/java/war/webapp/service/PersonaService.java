package war.webapp.service;

import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import war.webapp.model.Persona;

@WebService
@Path("/personas")
public interface PersonaService {
	
	 @GET
	    @Path("{id}")
	    Persona getPersona(@PathParam("id") String id_persona);

	    @GET
	    List<Persona> getPersonas();
	    
	    @POST
	    Persona savePersona(Persona persona) throws EntityExistsException;
	    
	    @DELETE
	    void removePersona(Persona persona);
}
