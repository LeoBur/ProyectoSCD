package war.webapp.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import war.webapp.model.Especialista;


@WebService
@Path("/especialistas")
public interface EspecialistaService extends PersonaService{
	
	@GET
    @Path("{tipo_esp}")
	List<Especialista> getEspecialistasByTipo(@PathParam("tipo_esp") String tipo_esp);

}
