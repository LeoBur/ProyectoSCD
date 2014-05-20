package war.webapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;

import war.webapp.dao.SintomaDao;
import war.webapp.model.Sintoma;
import war.webapp.service.SintomaManager;
import war.webapp.service.SintomaService;

@Service("sintomaManager")
@WebService(serviceName = "SintomaService", endpointInterface = "war.webapp.service.SintomaService")
public class SintomaManagerImpl extends GenericManagerImpl<Sintoma, Long> implements SintomaManager,SintomaService{

	private SintomaDao sintomaDao;
	
	
	@Override
	public void setSintomaDao(SintomaDao sintomaDao) {
		this.dao = sintomaDao;
		this.sintomaDao = sintomaDao;
		
	}
	
	@Override
	public Sintoma getSintoma(String idSintoma) {
		return sintomaDao.get(new Long (idSintoma));
	}
		
	@Override
	public Sintoma getSintoma(Long idSintoma) {
		return sintomaDao.get(idSintoma);
	}	
	
	@Override
	public List<Sintoma> getSintomas() {
		if (sintomaDao!=null){
			return sintomaDao.getAllDistinct();
		}
		return new ArrayList<Sintoma>();
	}
	
	@Override
	public Sintoma saveSintoma(final Sintoma sintoma)
			throws EntityExistsException {
		try {
			return sintomaDao.saveSintoma(sintoma);
		} catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("Sintoma ya existe");
		}
	}
	
	@Override
	public List<Sintoma> search(String searchTerm) {
		return super.search(searchTerm, Sintoma.class);
	}
	
	@Override
	public void removeSintoma(Sintoma sintoma) {
		log.debug("removing sintoma con id: " + sintoma.getIdSintoma());
        sintomaDao.remove(sintoma);
	}
	
	@Override
	public void removeSintoma(Long idSintoma) {
		log.debug("removing sintoma con id: " + idSintoma);
		sintomaDao.remove(idSintoma);
		
	}

	
}
