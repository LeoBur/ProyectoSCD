package com.bcpv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcpv.dao.MedicamentoDao;
import com.bcpv.model.Medicamento;
import com.bcpv.service.MedicamentoManager;
import com.bcpv.service.MedicamentoService;

@Service("medicamentoManager")
@WebService(serviceName = "MedicamentoService", endpointInterface = "com.bcpv.service.MedicamentoService")
public class MedicamentoManagerImpl extends GenericManagerImpl<Medicamento, Long> implements MedicamentoService, MedicamentoManager{

	private MedicamentoDao medicamentoDao;
	
	@Override
	@Autowired
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
