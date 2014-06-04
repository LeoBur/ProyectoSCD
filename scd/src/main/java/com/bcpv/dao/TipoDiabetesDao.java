package com.bcpv.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.bcpv.model.TipoDiabetes;

public interface TipoDiabetesDao extends GenericDao<TipoDiabetes, Long>{
	
	/**
     * Gets mediciones information based on login name.
     * @param id
     * @return medicion
     * @throws MedicionNotFoundException thrown when medicion not found in database
     */
    TipoDiabetes loadTipoDiabetesById(Long id) throws EntityNotFoundException;

    /**
     * Gets a list of mediciones
     *
     * @return List populated list of mediciones
     */
    List<TipoDiabetes> getTipoDiabeteses();

    /**
     * Saves a medicion.
     * @param medicion the object to be saved
     * @return the persisted Medicion object
     */
    TipoDiabetes saveTipoDiabetes(TipoDiabetes tipo);
}
