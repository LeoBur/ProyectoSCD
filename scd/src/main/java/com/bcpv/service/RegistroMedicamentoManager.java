package com.bcpv.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import com.bcpv.dao.RegistroMedicamentoDao;
import com.bcpv.model.RegistroMedicamento;

public interface RegistroMedicamentoManager {
	
	void setRegistroMedicamentoDao(RegistroMedicamentoDao registroMedicamentoDao);
	
	RegistroMedicamento getRegistroMedicamento(Long idRegMedicamento);
	
	RegistroMedicamento saveRegistroMedicamento (RegistroMedicamento regMedicamento) throws EntityExistsException;
	
	void removeRegistroMedicamento(RegistroMedicamento regMedicamento);
	
	void removeRegistroMedicamento(String idRegMedicamento);
	
	List<RegistroMedicamento> search(String searchTerm);

}
