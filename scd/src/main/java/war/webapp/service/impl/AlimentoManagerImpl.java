package war.webapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityExistsException;

import war.webapp.dao.AlimentoDao;
import war.webapp.model.Alimento;
import war.webapp.service.AlimentoManager;
import war.webapp.service.AlimentoService;
import javax.jws.WebService;

import org.springframework.stereotype.Service;

@Service("alimentoManager")
@WebService(serviceName = "AlimentoService", endpointInterface = "war.webapp.service.AlimentoService")
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
		log.debug("removing alimento: " + alimento.getIdAlimento());
        alimentoDao.remove(alimento);
	}

	@Override
	public void removeAlimento(Long idAlimento) {
		log.debug("removing alimento: " + idAlimento);
		alimentoDao.remove(idAlimento);
		
}

	
}
