package war.webapp.service;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import war.webapp.model.Persona;

@WebService
@Path("/endocrinologo")
public interface EndocrinologoService extends PersonaService{
	@GET
    @Path("{matricula}")
    Persona getEndocrinologoByMatricula(@PathParam("matricula") Long matricula);
}
