package com.bcpv.service;

import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.bcpv.model.Localidad;

@WebService
@Path("/localidades")
public interface LocalidadService {
	
	@GET
    @Path("{id}")
    Localidad getLocalidad(@PathParam("id") String id);

    @GET
    List<Localidad> getLocalidades();
    
    @POST
    Localidad saveLocalidad(Localidad localidad) throws EntityExistsException;
    
    @DELETE
    void removeLocalidad(Localidad localidad);

}
