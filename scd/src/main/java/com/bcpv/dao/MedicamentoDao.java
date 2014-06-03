package com.bcpv.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.bcpv.model.Medicamento;

public interface MedicamentoDao extends GenericDao<Medicamento, Long> {
	
	Medicamento loadMedicamentoById(Long idMedicamento) throws EntityNotFoundException;
	
	List<Medicamento> getMedicamentos();
	
	Medicamento save (Medicamento medicamento);

}
