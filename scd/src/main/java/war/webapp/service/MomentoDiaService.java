package war.webapp.service;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import war.webapp.model.MomentoDia;

@WebService
@Path("/momentosDia")
public interface MomentoDiaService {
	
	@GET
	@Path("{id}")
	MomentoDia getMomentoDia(@PathParam("id") String idMomentoD);
	
	@POST
	MomentoDia saveMomentoDia(MomentoDia momentoD) throws EntityExistsException;
	
	@DELETE
	void removeMomentoDia(MomentoDia momentoD);

}
