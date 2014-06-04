package com.bcpv.service;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.bcpv.model.Dieta;

@WebService
@Path("/dietas")
public interface DietaService {
	
	@GET
	@Path("{id}")
	Dieta getDieta(@PathParam("id") String idDieta);
	
	@POST
	Dieta saveDieta(Dieta idDieta) throws EntityExistsException;
	
	@DELETE
	void removeDieta(Dieta Dieta);

}
