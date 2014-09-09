package com.bcpv.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.bcpv.dao.EndocrinologoDao;
import com.bcpv.model.Endocrinologo;
import com.bcpv.model.Persona;

public interface EndocrinologoManager extends GenericManager<Endocrinologo, Long>{
	
	void setEndocrinologoDao(EndocrinologoDao endocrinologoDao);
	   
    Endocrinologo getEndocrinologo(Long id);

    List<Endocrinologo> getEndocrinologos();

    Endocrinologo saveEndocrinologo(Endocrinologo endocrinologo) throws EntityExistsException;
    
    void removeEndocrinologo(Endocrinologo endocrinologo);
    
    void removeEndocrinologo(String id);

    List<Endocrinologo> search(String searchTerm);
		
	Endocrinologo getEndocrinologoByMatricula(Long matricula);

    Endocrinologo getEndocrinologoByPersona(Persona persona) throws EntityNotFoundException;

}
