package com.bcpv.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.bcpv.model.Especialista;
import com.bcpv.model.Persona;
import com.bcpv.model.Especialista.TipoEspecialista;

public interface EspecialistaDao extends GenericDao<Especialista, Long>{
	
	Especialista loadEspecialistaByDNI(Long dni) throws EntityNotFoundException;

    List<Especialista> getEspecialistas();
    
    Especialista saveEspecialista(Especialista especialista);
	
	List<Especialista> getEspecialistaByTipo (String tipo);

    Especialista getEspecialistaByPersona(Persona persona) throws EntityNotFoundException;

    Especialista getEspecialista(Long matricula, TipoEspecialista tipoEspecialista);

    Especialista getEspecialista(Long idEspecialista) throws EntityNotFoundException;
}
