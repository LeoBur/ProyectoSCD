package com.bcpv.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import com.bcpv.dao.TipoDiabetesDao;
import com.bcpv.model.TipoDiabetes;

public interface TipoDiabetesManager extends GenericManager<TipoDiabetes, Long> {
	
	void setTipoDiabetesDao(TipoDiabetesDao tipoDao);
	
	TipoDiabetes getTipoDiabetes (int id_tipo);

    TipoDiabetes getTipoDiabetesByName (String tipo);
	
	List<TipoDiabetes> getTiposDiabetes();

    List<String> getTipoDiabetes();
	
	TipoDiabetes saveTipoDiabetes(TipoDiabetes tipo) throws EntityExistsException;
	
	void removeTipoDiabetes (TipoDiabetes tipo);
	
	void removeTipoDiabetes (String id_tipo);
	
	List<TipoDiabetes> search (String searchTerm);
}
