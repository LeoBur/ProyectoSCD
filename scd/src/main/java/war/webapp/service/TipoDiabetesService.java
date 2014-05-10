package war.webapp.service;

import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import war.webapp.model.TipoDiabetes;

@WebService
@Path("/tipoDiabetes")
public interface TipoDiabetesService {
	
	@GET
    @Path("{id}")
    TipoDiabetes getTipoDiabetes(@PathParam("id") String id_tipo);
	
	@GET
	List<TipoDiabetes> getTiposDiabetes();
	
	@POST
	TipoDiabetes saveTipoDiabetes (TipoDiabetes tipo) throws EntityExistsException;
	
	@DELETE
	void removeTipoDiabetes(TipoDiabetes tipo);

}
