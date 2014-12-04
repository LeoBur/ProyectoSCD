package com.bcpv.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import com.bcpv.dao.AlimentoDao;
import com.bcpv.model.Alimento;

public interface AlimentoManager extends GenericManager<Alimento, Long> {
	
	void setAlimentoDao(AlimentoDao alimentoDao);

	   
    Alimento getAlimento(Long idAlimento);

    /**
     * Retrieves a list of all mediciones.
     * @return List
     */
    List<Alimento> getAlimentos();

    /**
     * Saves a medicion's information.
     *
     * @param medicion
     * @return medicion the updated medicion object
     */
    Alimento saveAlimento(Alimento alimento) throws EntityExistsException;
    
    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeAlimento(Alimento alimento);
    
    /**
     * Removes a medicion from the database by their id_medicion
     *
     * @param id_medicion
     */
    void removeAlimento(Long idAlimento);

    /**
     * Search a medicion for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Alimento> search(String searchTerm);

    Alimento getByNombre(String nombre);
}

