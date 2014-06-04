package com.bcpv.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.bcpv.model.Medicion;

public interface MedicionDao extends GenericDao<Medicion, Long> {
	
	/**
     * Gets mediciones information based on login name.
     * @param id
     * @return medicion
     * @throws MedicionNotFoundException thrown when medicion not found in database
     */
    Medicion loadMedicionById(Long id) throws EntityNotFoundException;

    /**
     * Gets a list of mediciones
     *
     * @return List populated list of mediciones
     */
    List<Medicion> getMediciones();

    /**
     * Saves a medicion.
     * @param medicion the object to be saved
     * @return the persisted Medicion object
     */
    Medicion saveMedicion(Medicion medicion);

}
