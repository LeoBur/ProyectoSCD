package com.bcpv.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.bcpv.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcpv.dao.EndocrinologoDao;
import com.bcpv.model.Endocrinologo;
import com.bcpv.service.EndocrinologoManager;
import com.bcpv.service.EndocrinologoService;

@Service("endocrinologoManager")
@WebService(serviceName = "EndocrinologoService", endpointInterface = "com.bcpv.service.EndocrinologoService")
@Transactional
public class EndocrinologoManagerImpl extends GenericManagerImpl<Endocrinologo, Long> implements EndocrinologoManager, EndocrinologoService{

	private EndocrinologoDao endocrinologoDao;
	
	@Override
	@Autowired
	public void setEndocrinologoDao(EndocrinologoDao endocrinologoDao) {
		this.dao = endocrinologoDao;
		this.endocrinologoDao = endocrinologoDao;
		
	}
	
	@Override
	public Endocrinologo getEndocrinologo(String id) {
		return endocrinologoDao.get(new Long(id));
	}

	@Override
	public Endocrinologo getEndocrinologo(Long id) {
		return endocrinologoDao.get(id);
	}

	@Override
	public List<Endocrinologo> getEndocrinologos() {
		if (endocrinologoDao!=null){
			return endocrinologoDao.getAllDistinct();
		}
		return new ArrayList<Endocrinologo>();
	}

	@Override
	public Endocrinologo saveEndocrinologo(final Endocrinologo endocrinologo)
			throws EntityExistsException {
		try {
			return endocrinologoDao.saveEndocrinologo(endocrinologo);
		} catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("El endocrinologo ya existe");
		}
	}

	@Override
	public List<Endocrinologo> search(String searchTerm) {
		return super.search(searchTerm, Endocrinologo.class);
	}

	@Override
	public void removeEndocrinologo(Endocrinologo persona) {
		log.debug("removing persona: " + persona.getPersona().getFirstName()+ persona.getPersona().getFirstName());
        endocrinologoDao.remove(persona);
	}

	@Override
	public void removeEndocrinologo(String id) {
		log.debug("removing Endocrinologo: " + id);
		endocrinologoDao.remove(new Long(id));
		
	}
	
	@Override
	public Endocrinologo getEndocrinologoByMatricula(Long matricula) {
		return endocrinologoDao.getEndocrinologoByMatricula(matricula);
	}

    @Override
    public Endocrinologo getEndocrinologoByPersona(Persona persona) throws EntityNotFoundException{
        return endocrinologoDao.getEndocrinologoByPersona(persona);
    }
	
}
