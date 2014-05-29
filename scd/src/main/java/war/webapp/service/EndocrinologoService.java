package war.webapp.service;

import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import war.webapp.model.Endocrinologo;

@WebService
@Path("/endocrinologo")
public interface EndocrinologoService{
	
	@GET
    @Path("{id}")
    Endocrinologo getEndocrinologo(@PathParam("id") String id);

    @GET
    List<Endocrinologo> getEndocrinologos();
    
    @POST
    Endocrinologo saveEndocrinologo(Endocrinologo endo) throws EntityExistsException;
    
    @DELETE
    void removeEndocrinologo(Endocrinologo endo);
	
	@GET
    @Path("{matricula}")
    Endocrinologo getEndocrinologoByMatricula(@PathParam("matricula") Long matricula);
}
