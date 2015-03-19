package com.bcpv.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.bcpv.dao.EspecialistaDao;
import com.bcpv.model.Especialista;
import com.bcpv.model.Persona;
import com.bcpv.model.Especialista.TipoEspecialista;

public interface EspecialistaManager extends GenericManager<Especialista, Long>{
	
	void setEspecialistaDao(EspecialistaDao especialistaDao);
	   
    Especialista getEspecialista(Long id);

    List<Especialista> getEspecialistas();

    List<Especialista> getEspecialistasActivos();

    Especialista saveEspecialista(Especialista especialista) throws EntityExistsException;
    
    void removeEspecialista(Especialista especialista);
    
    void removeEspecialista(Long id);

    List<Especialista> search(String searchTerm);
		
	List<Especialista> getEspecialistasByTipo (String tipo_esp);

    Especialista getEspecialistaByPersona(Persona persona) throws EntityNotFoundException;

    Especialista getEspecialista(Long matricula, TipoEspecialista tipoEspecialista) throws EntityNotFoundException;
}
