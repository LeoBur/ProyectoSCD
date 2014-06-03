package com.bcpv.service;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.bcpv.model.Comida;

@WebService
@Path("/comidas")
public interface ComidaService {
	
	@GET
	@Path("{id}")
	Comida getComida(@PathParam("id") String idComida);
	
	@POST
	Comida saveComida(Comida comida) throws EntityExistsException;
	
	@DELETE
	void removeComida(Comida comida);

}
