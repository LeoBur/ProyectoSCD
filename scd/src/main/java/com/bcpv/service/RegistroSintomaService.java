package com.bcpv.service;

import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.bcpv.model.RegistroSintoma;

@WebService
@Path("/registroSintoma")
public interface RegistroSintomaService {
	
	@GET
	@Path("{id}")
	RegistroSintoma getRegistroSintoma(@PathParam("id") String idRegistroSintoma);
	
	@GET
    List<RegistroSintoma> getRegistrosSintomas();
	
	@POST
	RegistroSintoma saveRegistroSintoma(RegistroSintoma registroSintoma) throws EntityExistsException;
	
	@DELETE
	void removeRegistroSintoma(RegistroSintoma registroSintoma);

}
