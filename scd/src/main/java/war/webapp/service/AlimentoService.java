package war.webapp.service;

import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import war.webapp.model.Alimento;

@WebService
@Path("/alimentos")
public interface AlimentoService {
	/**
     * Retrieves a alimento by idAlimento.  An exception is thrown if alimento not found
     *
     * @param idAlimento the identifier for the alimento
     * @return Alimento
     */
    @GET
    @Path("{id}")
    Alimento getAlimento(@PathParam("id") String idAlimento);

    /**
     * Retrieves a list of all alimentos.
     *
     * @return List
     */
    @GET
    List<Alimento> getAlimentos();

    /**
     * Saves a alimento
     *
     * @param alimento
     * @return updated alimento
     * @throws EntityExistsException thrown when alimento already exists
     */
    @POST
    Alimento saveAlimento(Alimento alimento) throws EntityExistsException;
    
    /**
     * Removes a alimento from the database
     *
     * @param alimento
     */
    @DELETE
    void removeAlimento(Alimento alimento);

}
