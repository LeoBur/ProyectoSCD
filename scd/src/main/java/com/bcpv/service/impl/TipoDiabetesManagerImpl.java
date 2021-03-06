package com.bcpv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcpv.dao.TipoDiabetesDao;
import com.bcpv.model.TipoDiabetes;
import com.bcpv.service.TipoDiabetesManager;
import com.bcpv.service.TipoDiabetesService;

@Service("tipoDiabetesManager")
@WebService(serviceName = "TipoDiabetesService", endpointInterface = "com.bcpv.service.TipoDiabetesService")
@Transactional
public class TipoDiabetesManagerImpl extends GenericManagerImpl<TipoDiabetes, Long> implements TipoDiabetesService, TipoDiabetesManager{

	private TipoDiabetesDao tipoDiabetesDao;
	
	@Override
	@Autowired
	public void setTipoDiabetesDao(TipoDiabetesDao tipoDao){
		this.dao = tipoDao;
		this.tipoDiabetesDao = tipoDao;
	}
	
	@Override
	public TipoDiabetes getTipoDiabetes (String id_tipo){
		return tipoDiabetesDao.get(new Long (id_tipo));
	}
	
	@Override
	public TipoDiabetes getTipoDiabetes (int id_tipo){
		return tipoDiabetesDao.loadTipoDiabetesById(id_tipo);
	}

    @Override
    public TipoDiabetes getTipoDiabetesByName (String tipo) {
        return tipoDiabetesDao.loadTipoDiabetesByTipo(tipo);
    }
	
	@Override
	public List<TipoDiabetes> getTiposDiabetes() {
		if (tipoDiabetesDao != null){
			return tipoDiabetesDao.getAllDistinct();
		}
		return new ArrayList<TipoDiabetes>();
	}

    @Override
    public List<String> getTipoDiabetes() {
        if (tipoDiabetesDao != null){
            return tipoDiabetesDao.getTipoDiabetes();
        }
        return new ArrayList<String>();
    }
	
	@Override
	public TipoDiabetes saveTipoDiabetes(final TipoDiabetes tipo) throws EntityExistsException {
		try {
			return tipoDiabetesDao.saveTipoDiabetes(tipo);
		} catch (final EntityExistsException e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("Tipo de diabetes ya existe");
		}
	}

	@Override
	public void removeTipoDiabetes(TipoDiabetes tipo) {
		log.debug("removing tipo de diabetes: " + tipo.getId_tipo());
        tipoDiabetesDao.remove(tipo);
	}

	@Override
	public void removeTipoDiabetes(String id_tipo) {
		log.debug("removing medicion: " + id_tipo);
		tipoDiabetesDao.remove(new Long(id_tipo));
		
	}
	
	@Override
	public List<TipoDiabetes> search(String searchTerm) {
		return super.search(searchTerm, TipoDiabetes.class);
	}
}
