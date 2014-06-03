package com.bcpv.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import com.bcpv.dao.DepartamentoDao;
import com.bcpv.model.Departamento;

public interface DepartamentoManager extends GenericManager<Departamento, Long>{
	
	void setDepartamentoDao(DepartamentoDao departamentoDao);
	   
    Departamento getDepartamento(Long id_departamento);

    List<Departamento> getDepartamentos();

    Departamento saveDepartamento(Departamento departamento) throws EntityExistsException;
    
    void removeDepartamento(Departamento departamento);
    
    void removeDepartamento(String id_departamento);

    List<Departamento> search(String searchTerm);

}
