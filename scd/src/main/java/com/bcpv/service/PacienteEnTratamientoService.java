package com.bcpv.service;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.bcpv.model.PacienteEnTratamiento;

@WebService
@Path("/pacientesEnTratamiento")
public interface PacienteEnTratamientoService {
	
	@GET
	@Path("{id}")
	PacienteEnTratamiento getPacienteEnTratamiento(@PathParam("id") String idPacienteEnTratamiento);
	
	@POST
	PacienteEnTratamiento savePacienteEnTratamiento(PacienteEnTratamiento pacienteEnTratamiento);
	
	@DELETE
	void removePacienteEnTratamiento(PacienteEnTratamiento pacienteEnTratamiento);

}
