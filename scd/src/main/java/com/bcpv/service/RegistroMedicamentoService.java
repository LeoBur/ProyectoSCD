package com.bcpv.service;

import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.bcpv.model.RegistroMedicamento;

@WebService
@Path("/registroMedicamento")
public interface RegistroMedicamentoService {
	
	@GET
	@Path("{id}")
	RegistroMedicamento getRegistroMedicamento(@PathParam("id") String idRegistroMedicamento);
	
	@GET
    List<RegistroMedicamento> getRegistrosMedicamentos();
	
	@POST
	RegistroMedicamento saveRegistroMedicamento(RegistroMedicamento registroMedicamento) throws EntityExistsException;
	
	@DELETE
	void removeRegistroMedicamento(RegistroMedicamento registroMedicamento);

}
