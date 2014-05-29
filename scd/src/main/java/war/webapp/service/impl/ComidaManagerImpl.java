package war.webapp.service.impl;

import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;

import war.webapp.dao.ComidaDao;
import war.webapp.model.Comida;
import war.webapp.service.ComidaManager;
import war.webapp.service.ComidaService;

@Service("comidaManager")
@WebService(serviceName="ComidaService", endpointInterface="war.webapp.service.ComidaService")
public class ComidaManagerImpl extends GenericManagerImpl<Comida, Long> implements ComidaManager, ComidaService{

	private ComidaDao comidaDao;
	
	@Override
	public Comida getComida(String idComida) {
		return comidaDao.get(new Long (idComida));
	}

	@Override
	public void setComidaDao(ComidaDao comidaDao) {
		this.dao=comidaDao;
		this.comidaDao=comidaDao;
		
	}

	@Override
	public Comida getComida(Long idComida) {
		return comidaDao.get(idComida);
	}

	@Override
	public Comida saveComida(Comida comida) throws EntityExistsException {
		try{
			return comidaDao.save(comida);
		}
		catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("Comida ya existe");
		}
	}

	@Override
	public void removeComida(Comida comida) {
		log.debug("removing comida id: "+comida.getIdComida());
		comidaDao.remove(comida);
		
	}

	@Override
	public void removeComida(String idComida) {
		log.debug("removing comida id: "+idComida);
		comidaDao.remove(new Long(idComida));
		
	}

	@Override
	public List<Comida> search(String searchTerm) {
		return super.search(searchTerm, Comida.class);
	}

}
