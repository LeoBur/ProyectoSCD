package com.bcpv.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.bcpv.model.RegistroMedicamento;

public interface RegistroMedicamentoDao extends GenericDao<RegistroMedicamento, Long>{
	
	RegistroMedicamento loadRegistroMedicamentoById(Long idRegMedicamento) throws EntityNotFoundException;
	
	List<RegistroMedicamento> getRegistroMedicamento();
	
	RegistroMedicamento saveRegistroMedicamento(RegistroMedicamento regMedicamento);

}
