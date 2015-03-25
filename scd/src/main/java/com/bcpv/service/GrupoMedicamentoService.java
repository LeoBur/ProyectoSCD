package com.bcpv.service;

import com.bcpv.model.GrupoMedicamento;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * Created by Leo on 24/03/2015.
 */
@WebService
@Path("/gruposMedicamento")
public interface GrupoMedicamentoService {

    @GET
    @Path("{id}")
    GrupoMedicamento getGrupoMedicamento(@PathParam("id") String idGrupoMedicamento);

    @GET
    List<GrupoMedicamento> getGrupoMedicamentos();

    @POST
    GrupoMedicamento saveGrupoMedicamento(GrupoMedicamento alimento) throws EntityExistsException;

    @DELETE
    void removeGrupoMedicamento(GrupoMedicamento alimento);
}
