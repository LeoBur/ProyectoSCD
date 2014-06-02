package war.webapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;

import war.webapp.dao.DepartamentoDao;
import war.webapp.model.Departamento;
import war.webapp.service.DepartamentoManager;
import war.webapp.service.DepartamentoService;

@Service("departamentoManager")
@WebService(serviceName = "DepartamentoService", endpointInterface = "war.webapp.service.DepartamentoService")
public class DepartamentoManagerImpl extends GenericManagerImpl<Departamento, Long> implements DepartamentoService, DepartamentoManager{

private DepartamentoDao departamentoDao;
	
	@Override
	public void setDepartamentoDao(DepartamentoDao departamentoDao) {
		this.dao = departamentoDao;
		this.departamentoDao = departamentoDao;
		
	}
	
	@Override
	public Departamento getDepartamento(String id_departamento) {
		return departamentoDao.get(new Long(id_departamento));
	}

	@Override
	public Departamento getDepartamento(Long id_departamento) {
		return departamentoDao.get(id_departamento);
	}

	@Override
	public List<Departamento> getDepartamentos() {
		if (departamentoDao!=null){
			return departamentoDao.getAllDistinct();
		}
		return new ArrayList<Departamento>();
	}

	@Override
	public Departamento saveDepartamento(final Departamento departamento)
			throws EntityExistsException {
		try {
			return departamentoDao.saveDepartamento(departamento);
		} catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("La departamento ya existe");
		}
	}

	@Override
	public List<Departamento> search(String searchTerm) {
		return super.search(searchTerm, Departamento.class);
	}

	@Override
	public void removeDepartamento(Departamento departamento) {
		log.debug("removing departamento: " + departamento.getNombre());
        departamentoDao.remove(departamento);
	}

	@Override
	public void removeDepartamento(String id_departamento) {
		log.debug("removing departamento: " + id_departamento);
		departamentoDao.remove(new Long(id_departamento));
		
	}
}
