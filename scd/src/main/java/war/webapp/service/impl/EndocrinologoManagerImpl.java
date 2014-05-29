package war.webapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;

import war.webapp.dao.EndocrinologoDao;
import war.webapp.model.Endocrinologo;
import war.webapp.service.EndocrinologoManager;
import war.webapp.service.EndocrinologoService;

@Service("endocrinologoManager")
@WebService(serviceName = "EndocrinologoService", endpointInterface = "war.webapp.service.EndocrinologoService")
public class EndocrinologoManagerImpl extends GenericManagerImpl<Endocrinologo, Long> implements EndocrinologoManager, EndocrinologoService{

	private EndocrinologoDao endocrinologoDao;
	
	@Override
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
	public Endocrinologo saveEndocrinologo(final Endocrinologo persona)
			throws EntityExistsException {
		try {
			return endocrinologoDao.saveEndocrinologo(persona);
		} catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("La persona ya existe");
		}
	}

	@Override
	public List<Endocrinologo> search(String searchTerm) {
		return super.search(searchTerm, Endocrinologo.class);
	}

	@Override
	public void removeEndocrinologo(Endocrinologo persona) {
		log.debug("removing persona: " + persona.getNombre() + persona.getApellido());
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
	
}
