package com.bcpv.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import com.bcpv.dao.EspecialistaDao;
import com.bcpv.model.Especialista;

public interface EspecialistaManager extends GenericManager<Especialista, Long>{
	
	void setEspecialistaDao(EspecialistaDao especialistaDao);
	   
    Especialista getEspecialista(Long id);

    List<Especialista> getEspecialistas();

    Especialista saveEspecialista(Especialista especialista) throws EntityExistsException;
    
    void removeEspecialista(Especialista especialista);
    
    void removeEspecialista(String id);

    List<Especialista> search(String searchTerm);
		
	List<Especialista> getEspecialistasByTipo (String tipo_esp);
	
}
