package com.bcpv.service;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.bcpv.model.DiaDieta;

@WebService
@Path("/diasDieta")
public interface DiaDietaService {

	@GET
	@Path("{id}")
	DiaDieta getDiaDieta(@PathParam("id") String idDiaDieta);
	
	@POST
	DiaDieta saveDiaDieta(DiaDieta idDiaDieta) throws EntityExistsException;
	
	@DELETE
	void removeDiaDieta(DiaDieta diaDieta);
}
