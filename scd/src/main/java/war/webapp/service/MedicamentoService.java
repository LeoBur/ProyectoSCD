package war.webapp.service;

import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import war.webapp.model.Medicamento;

@WebService
@Path("/medicamentos")
public interface MedicamentoService {
	
	@GET
	@Path("{id}")
	Medicamento getMedicamento(@PathParam("id") String Medicamento);
	
	@GET
	List<Medicamento> getMedicamentos();
	
	@POST
	Medicamento saveMedicamento(Medicamento medicamento) throws EntityExistsException;
	
	@DELETE
	void removeMedicamento(Medicamento medicamento);
}
