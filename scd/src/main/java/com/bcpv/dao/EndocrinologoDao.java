package com.bcpv.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.bcpv.model.Endocrinologo;
import com.bcpv.model.Persona;

public interface EndocrinologoDao extends GenericDao<Endocrinologo, Long>{
	
	Endocrinologo loadEndocrinologoByDNI(Long dni) throws EntityNotFoundException;

    List<Endocrinologo> getEndocrinologos();
    
    Endocrinologo saveEndocrinologo(Endocrinologo endocrinologo);
	
	Endocrinologo getEndocrinologoByMatricula (Long matricula);
	
	Long loadIdEndocrinologoByUsername(String username) throws EntityNotFoundException;

    Endocrinologo getEndocrinologoByPersona(Persona persona) throws EntityNotFoundException;

}
