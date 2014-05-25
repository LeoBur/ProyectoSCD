package war.webapp.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import war.webapp.model.Paciente;
import war.webapp.model.TipoDiabetes;

@WebService
@Path("/pacientes")
public interface PacienteService {

	@GET
    @Path("{tipo}")
    List<Paciente> getPacientesByTipo(@PathParam("tipo") TipoDiabetes tipo);
}
