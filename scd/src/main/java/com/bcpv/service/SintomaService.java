package com.bcpv.service;

import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.bcpv.model.Sintoma;

@WebService
@Path("/sintomas")
public interface SintomaService {

	@GET
	@Path("{id}")
	Sintoma getSintoma(@PathParam("id") String idSintoma);
	
	@GET
	List<Sintoma> getSintomas();
	
	@POST
	Sintoma saveSintoma(Sintoma sintoma) throws EntityExistsException;
	
	@DELETE
	void removeSintoma(Sintoma sintoma);
}
