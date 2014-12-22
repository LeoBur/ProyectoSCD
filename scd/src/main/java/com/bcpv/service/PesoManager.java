package com.bcpv.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import com.bcpv.dao.PesoDao;
import com.bcpv.model.Peso;

public interface PesoManager extends GenericManager<Peso, Long>{

	void setPesoDao(PesoDao pesoDao);
	
	Peso getPeso(Long idPeso);
	
	List<Peso> getPesos();
	
	Peso savePeso(Peso peso) throws EntityExistsException;
	
	void removePeso(Peso peso);
	
	void removePeso(String idPeso);
	
	List<Peso> search(String searchTerm);

    List<Peso> getPesosByIdPaciente(Long idPaciente);
}
