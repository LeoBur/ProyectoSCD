package com.bcpv.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import com.bcpv.dao.EndocrinologoDao;
import com.bcpv.model.Endocrinologo;

public interface EndocrinologoManager extends GenericManager<Endocrinologo, Long>{
	
	void setEndocrinologoDao(EndocrinologoDao endocrinologoDao);
	   
    Endocrinologo getEndocrinologo(Long id);

    List<Endocrinologo> getEndocrinologos();

    Endocrinologo saveEndocrinologo(Endocrinologo endocrinologo) throws EntityExistsException;
    
    void removeEndocrinologo(Endocrinologo endocrinologo);
    
    void removeEndocrinologo(String id);

    List<Endocrinologo> search(String searchTerm);
		
	Endocrinologo getEndocrinologoByMatricula(Long matricula);

}
