package com.bcpv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;

import com.bcpv.dao.AlimentoDao;
import com.bcpv.model.Alimento;
import com.bcpv.service.AlimentoManager;
import com.bcpv.service.AlimentoService;

@Service("alimentoManager")
@WebService(serviceName = "AlimentoService", endpointInterface = "com.bcpv.webapp.service.AlimentoService")
public class AlimentoManagerImpl extends GenericManagerImpl<Alimento, Long> implements AlimentoManager, AlimentoService {

	private AlimentoDao alimentoDao;
	
	@Override
	public void setAlimentoDao(AlimentoDao alimentoDao) {
		this.dao = alimentoDao;
		this.alimentoDao = alimentoDao;
		
	}
	
	@Override
	public Alimento getAlimento(String idAlimento) {
		return alimentoDao.get(new Long(idAlimento));
	}

	@Override
	public Alimento getAlimento(Long idAlimento) {
		return alimentoDao.get(idAlimento);
	}

	@Override
	public List<Alimento> getAlimentos() {
		if (alimentoDao!=null){
			return alimentoDao.getAllDistinct();
		}
		return new ArrayList<Alimento>();
	}

	@Override
	public Alimento saveAlimento(final Alimento alimento)
			throws EntityExistsException {
		try {
			return alimentoDao.saveAlimento(alimento);
		} catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("Alimento ya existe");
		}
	}

	@Override
	public List<Alimento> search(String searchTerm) {
		return super.search(searchTerm, Alimento.class);
	}

	@Override
	public void removeAlimento(Alimento alimento) {
		log.debug("removing alimento con id: " + alimento.getIdAlimento());
        alimentoDao.remove(alimento);
	}

	@Override
	public void removeAlimento(Long idAlimento) {
		log.debug("removing alimento con id: " + idAlimento);
		alimentoDao.remove(idAlimento);
		
	}

	
}
