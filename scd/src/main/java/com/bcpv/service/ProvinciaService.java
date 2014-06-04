package com.bcpv.service;

import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.bcpv.model.Provincia;

@WebService
@Path("/provincias")
public interface ProvinciaService {

	@GET
    @Path("{id}")
    Provincia getProvincia(@PathParam("id") String id_provincia);

    @GET
    List<Provincia> getProvincias();
    
    @POST
    Provincia saveProvincia(Provincia provincia) throws EntityExistsException;
    
    @DELETE
    void removeProvincia(Provincia provincia);
}
