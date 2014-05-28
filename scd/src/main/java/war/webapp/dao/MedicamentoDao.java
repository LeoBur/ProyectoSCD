package war.webapp.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import war.webapp.model.Medicamento;

public interface MedicamentoDao extends GenericDao<Medicamento, Long> {
	
	Medicamento loadMedicamentoById(Long idMedicamento) throws EntityNotFoundException;
	
	List<Medicamento> getMedicamentos();
	
	Medicamento save (Medicamento medicamento);

}
