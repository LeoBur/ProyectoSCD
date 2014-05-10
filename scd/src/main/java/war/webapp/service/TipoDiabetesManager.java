package war.webapp.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import war.webapp.dao.TipoDiabetesDao;
import war.webapp.model.TipoDiabetes;

public interface TipoDiabetesManager extends GenericManager<TipoDiabetes, Long> {
	
	void setTipoDiabetesDao(TipoDiabetesDao tipoDao);
	
	TipoDiabetes getTipoDiabetes (Long id_tipo);
	
	List<TipoDiabetes> getTiposDiabetes();
	
	TipoDiabetes saveTipoDiabetes(TipoDiabetes tipo) throws EntityExistsException;
	
	void removeTipoDiabetes (TipoDiabetes tipo);
	
	void removeTipoDiabetes (String id_tipo);
	
	List<TipoDiabetes> search (String searchTerm);
}
