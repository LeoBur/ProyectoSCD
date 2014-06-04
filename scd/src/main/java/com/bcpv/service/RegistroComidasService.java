package com.bcpv.service;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.bcpv.model.RegistroComidas;

import java.util.List;

@WebService
@Path("/registroComidas")
public interface RegistroComidasService {

	@GET
	@Path("{id}")
	RegistroComidas getRegistroComidas(@PathParam("id") String idRegistroComidas);
	
	@GET
    List<RegistroComidas> getRegistrosComidas();
	
	@POST
	RegistroComidas saveRegistroComidas(RegistroComidas registroComidas) throws EntityExistsException;
	
	@DELETE
	void removeRegistroComidas(RegistroComidas registroComidas);
}
