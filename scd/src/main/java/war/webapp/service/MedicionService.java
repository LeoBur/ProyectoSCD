package war.webapp.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import war.webapp.model.Medicion;

/**
 * Web Service interface so hierarchy of Generic Manager isn't carried through.
 */
@WebService
@Path("/mediciones")
public interface MedicionService {
	/**
     * Retrieves a medicion by medicionId.  An exception is thrown if medicion not found
     *
     * @param medicionId the identifier for the medicion
     * @return Medicion
     */
    @GET
    @Path("{id}")
    Medicion getMedicion(@PathParam("id") String id_medicion);

    /**
     * Retrieves a list of all mediciones.
     *
     * @return List
     */
    @GET
    List<Medicion> getMediciones();

    /**
     * Saves a medicion
     *
     * @param medicion
     * @return updated medicion
     * @throws MedicionExistsException thrown when medicion already exists
     */
    @POST
    Medicion saveMedicion(Medicion medicion) throws MedicionExistsException;
    
    /**
     * Removes a medicion from the database
     *
     * @param medicion
     */
    @DELETE
    void removeMedicion(Medicion medicion);

}
