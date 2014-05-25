package war.webapp.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import war.webapp.dao.MedicionDao;
import war.webapp.model.Medicion;

public interface MedicionManager extends GenericManager<Medicion, Long>{
	
	void setMedicionDao(MedicionDao medicionDao);

	   
    Medicion getMedicion(Long id_medicion);

    /**
     * Retrieves a list of all mediciones.
     * @return List
     */
    List<Medicion> getMediciones();

    /**
     * Saves a medicion's information.
     *
     * @param medicion
     * @return medicion the updated medicion object
     */
    Medicion saveMedicion(Medicion medicion) throws EntityExistsException;
    
    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeMedicion(Medicion medicion);
    
    /**
     * Removes a medicion from the database by their id_medicion
     *
     * @param id_medicion
     */
    void removeMedicion(String id_medicion);

    /**
     * Search a medicion for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Medicion> search(String searchTerm);
}
