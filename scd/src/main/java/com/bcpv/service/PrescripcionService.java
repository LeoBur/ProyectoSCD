package com.bcpv.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.bcpv.model.Prescripcion;

@WebService
@Path("/prescripciones")
public interface PrescripcionService {
	
	@GET
	@Path("{id}")
	Prescripcion getPrescripcion(@PathParam("id") String idPrescripcion);
	
	@GET
	List<Prescripcion> getPrescripcionesByIdTratamiento(Long idTratamiento); 
	
	@POST
	Prescripcion savePrescripcion(Prescripcion prescripcion);
	
	@DELETE
	void removePrescripcion(Prescripcion prescripcion);

}
