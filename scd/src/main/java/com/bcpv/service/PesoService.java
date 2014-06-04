package com.bcpv.service;

import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.bcpv.model.Peso;

@WebService
@Path("/pesos")
public interface PesoService {
	
	@GET
	@PathParam("{id}")
	Peso getPeso(@PathParam("id")String id);
	
	@GET
	List<Peso> getPesos();
	
	@POST
	Peso savePeso(Peso peso) throws EntityExistsException;
	
	@DELETE
	void removePeso(Peso peso);
	

}
