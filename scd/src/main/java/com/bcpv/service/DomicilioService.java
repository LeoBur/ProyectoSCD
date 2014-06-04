package com.bcpv.service;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.bcpv.model.Domicilio;

@WebService
@Path("/domicilios")
public interface DomicilioService {
	
	@GET
	@Path("{id}")
	Domicilio getDomicilio(@PathParam("id") String idDomicilio);
	
	@POST
	Domicilio saveDomicilio(Domicilio domicilio) throws EntityExistsException;
	
	@DELETE
	void removeDomicilio(Domicilio domicilio);

}
