package war.webapp.service;

import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import war.webapp.model.Paciente;
import war.webapp.model.TipoDiabetes;

@WebService
@Path("/pacientes")
public interface PacienteService {

	@GET
    @Path("{id}")
    Paciente getPaciente(@PathParam("id") String id);

    @GET
    List<Paciente> getPacientes();
    
    @POST
    Paciente savePaciente(Paciente paciente) throws EntityExistsException;
    
    @DELETE
    void removePaciente(Paciente paciente);
	
	@GET
    @Path("{tipo}")
    List<Paciente> getPacientesByTipo(@PathParam("tipo") TipoDiabetes tipo);
}
