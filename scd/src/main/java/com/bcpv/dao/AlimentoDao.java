/**
 * 
 */
package com.bcpv.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.bcpv.model.Alimento;

/**
 * @author marcos
 *
 */
public interface AlimentoDao extends GenericDao<Alimento, Long> {
	
	/**
     * Gets alimento information based on login name.
     * @param id
     * @return alimento
     * @throws EntityNotFoundException thrown when alimento not found in database
     */
    Alimento loadAlimentoById(Long id) throws EntityNotFoundException;
    
    /**
     * Gets a list of alimentos
     *
     * @return List populated list of alimentos
     */
    List<Alimento> getAlimentos();
    
    /**
     * Saves a alimento.
     * @param alimento the object to be saved
     * @return the persisted Alimento object
     */
    Alimento saveAlimento(Alimento alimento);

}
