package war.webapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import war.webapp.dao.MedicamentoDao;
import war.webapp.model.Medicamento;
import war.webapp.service.MedicamentoManager;
import war.webapp.service.MedicamentoService;

public class MedicamentoManagerImpl extends GenericManagerImpl<Medicamento, Long> implements MedicamentoService, MedicamentoManager{

	private MedicamentoDao medicamentoDao;
	
	@Override
	public void setMedicamentoDao(MedicamentoDao medicamentoDao){
		this.dao = medicamentoDao;
		this.medicamentoDao = medicamentoDao;
		
	}
	
	
	@Override
	public Medicamento getMedicamento(Long idMedicamento) {
		return medicamentoDao.get(idMedicamento);
	}

	@Override
	public void removeMedicamento(Long idMedicamento) {
		log.debug("eliminando medicamento con id: "+idMedicamento);
		medicamentoDao.remove(idMedicamento);
		
	}

	@Override
	public List<Medicamento> search(String searchTerm) {
		return super.search(searchTerm, Medicamento.class);
	}

	@Override
	public Medicamento getMedicamento(String idMedicamento) {
		return medicamentoDao.get(new Long(idMedicamento));
	}

	@Override
	public List<Medicamento> getMedicamentos() {
		if (medicamentoDao != null){
			return medicamentoDao.getAllDistinct();
		}
		else
			return new ArrayList<Medicamento>();
	}

	@Override
	public Medicamento saveMedicamento(Medicamento medicamento)
			throws EntityExistsException {
		try {
			return medicamentoDao.save(medicamento);
		}
		catch (Exception e) {
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("Medicamento ya existe");
			
		}
	}

	@Override
	public void removeMedicamento(Medicamento medicamento) {
		log.debug("eliminando medicamento con id: "+medicamento.getIdMedicamento());
		medicamentoDao.remove(medicamento);		
	}

}
