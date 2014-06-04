package com.bcpv.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.bcpv.model.Tratamiento;

@WebService
@Path("/tratamientos")
public interface TratamientoService {
	
	@GET
	@Path("{id}")
	Tratamiento getTratamiento(@PathParam("id") String idTratamiento);
	
	@GET
	List<Tratamiento> getTratamientosByIdPaciente();      //FALTA AGREGAR EL PARÁMETRO DEL IDPACIENTE*/
	
	@POST
	Tratamiento saveTratamiento(Tratamiento tratamiento);
	
	@DELETE
	void removeTratamiento(Tratamiento tratamiento);
	
	@GET
	Tratamiento getUltimoTratamientoByIdPaciente();       //FALTA AGREGAR EL PARÁMETRO DEL IDPACIENTE*/

}
