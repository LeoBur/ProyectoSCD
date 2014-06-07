package com.bcpv.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.bcpv.model.Departamento;

public interface DepartamentoDao extends GenericDao<Departamento, Long> {
	
    Departamento loadDepartamentoByNombre(String nombre) throws EntityNotFoundException;

    List<Departamento> getDepartamentos();
    
    Departamento saveDepartamento (Departamento departamento);

}
