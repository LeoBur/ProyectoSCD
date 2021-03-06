package com.bcpv.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import com.bcpv.dao.MedicamentoDao;
import com.bcpv.model.Medicamento;

public interface MedicamentoManager extends GenericManager<Medicamento, Long>{
	
	void setMedicamentoDao (MedicamentoDao medicamentoDao);
	
	Medicamento getMedicamento(Long idMedicamento);
	
	List<Medicamento> getMedicamentos();
	
	Medicamento saveMedicamento(Medicamento medicamento) throws EntityExistsException;
	
	void removeMedicamento(Medicamento medicamento);
	
	void removeMedicamento(Long idMedicamento);
	
	List<Medicamento> search(String searchTerm);

	Medicamento getByNombreComercial(String nombreComercial);
}
