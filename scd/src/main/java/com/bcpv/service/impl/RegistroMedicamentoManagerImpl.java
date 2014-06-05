package com.bcpv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;

import com.bcpv.dao.RegistroMedicamentoDao;
import com.bcpv.model.RegistroMedicamento;
import com.bcpv.service.RegistroMedicamentoManager;
import com.bcpv.service.RegistroMedicamentoService;

@Service("registroMedicamentoManager")
@WebService(serviceName = "RegistroMedicamentoService", endpointInterface = "com.bcpv.service.RegistroMedicamentoService")
public class RegistroMedicamentoManagerImpl extends GenericManagerImpl<RegistroMedicamento, Long> implements RegistroMedicamentoManager, RegistroMedicamentoService{

	private RegistroMedicamentoDao registroMedicamentoDao;

	@Override
	public void setRegistroMedicamentoDao(RegistroMedicamentoDao registroMedicamentoDao) {
		this.dao=registroMedicamentoDao;
		this.registroMedicamentoDao=registroMedicamentoDao;
		
	}

	@Override
	public RegistroMedicamento getRegistroMedicamento(Long idRegMedicamento) {
		return registroMedicamentoDao.get(idRegMedicamento);
	}

	@Override
	public void removeRegistroMedicamento(String idRegMedicamento) {
		log.debug("removing registroMedicamento id: "+idRegMedicamento);
		registroMedicamentoDao.remove(new Long(idRegMedicamento));
		
	}

	@Override
	public List<RegistroMedicamento> search(String searchTerm) {
		return super.search(searchTerm, RegistroMedicamento.class);
	}

	@Override
	public RegistroMedicamento getRegistroMedicamento(String idRegistroMedicamento) {
		return registroMedicamentoDao.get(new Long(idRegistroMedicamento));
	}

	@Override
	public List<RegistroMedicamento> getRegistrosMedicamentos() {
		if (registroMedicamentoDao!=null){
			return registroMedicamentoDao.getAllDistinct();
		}
		return new ArrayList<RegistroMedicamento>();
	}

	@Override
	public RegistroMedicamento saveRegistroMedicamento(RegistroMedicamento registroMedicamento)
			throws EntityExistsException {
		try{
			return registroMedicamentoDao.save(registroMedicamento);
		} 
		catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("RegistroMedicamento ya existe");
		}
	}

	@Override
	public void removeRegistroMedicamento(RegistroMedicamento registroMedicamento) {
		log.debug("removing registroMedicamento id: "+registroMedicamento.getIdRegMedicamento());
		registroMedicamentoDao.remove(registroMedicamento);
		
	}
}
