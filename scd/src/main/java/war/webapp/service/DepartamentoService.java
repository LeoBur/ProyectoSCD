package war.webapp.service;

import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import war.webapp.model.Departamento;

@WebService
@Path("/departamentos")
public interface DepartamentoService {
	
	@GET
    @Path("{id}")
    Departamento getDepartamento(@PathParam("id") String id_departamento);

    @GET
    List<Departamento> getDepartamentos();
    
    @POST
    Departamento saveDepartamento(Departamento departamento) throws EntityExistsException;
    
    @DELETE
    void removeDepartamento(Departamento departamento);
}
