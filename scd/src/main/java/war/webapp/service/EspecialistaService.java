package war.webapp.service;

import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import war.webapp.model.Especialista;


@WebService
@Path("/especialistas")
public interface EspecialistaService{
	
	@GET
    @Path("{id}")
    Especialista getEspecialista(@PathParam("id") String id);

    @GET
    List<Especialista> getEspecialistas();
    
    @POST
    Especialista saveEspecialista(Especialista especialista) throws EntityExistsException;
    
    @DELETE
    void removeEspecialista(Especialista especialista);
	
	@GET
    @Path("{tipo_esp}")
	List<Especialista> getEspecialistasByTipo(@PathParam("tipo_esp") String tipo_esp);

}
