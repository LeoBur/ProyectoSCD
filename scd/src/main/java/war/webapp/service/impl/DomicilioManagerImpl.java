package war.webapp.service.impl;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;

import javax.jws.WebService;

import war.webapp.dao.DomicilioDao;
import war.webapp.model.Domicilio;
import war.webapp.service.DomicilioManager;
import war.webapp.service.DomicilioService;

@Service("domicilioManager")
@WebService(serviceName="DomicilioService", endpointInterface = "war.webapp.service.DomicilioService")
public class DomicilioManagerImpl extends GenericManagerImpl<Domicilio, Long> implements DomicilioService,DomicilioManager{

	private DomicilioDao domicilioDao;
	
	
	@Override
	public void setDomicilioDao(DomicilioDao domicilioDao) {
		this.dao=domicilioDao;
		this.domicilioDao=domicilioDao;
		
	}

	@Override
	public Domicilio getDomicilio(Long idDomicilio) {
		return domicilioDao.get(idDomicilio);
	}

	@Override
	public void removeDomicilio(String idDomicilio) {
		log.debug("removing Domicilio id: "+idDomicilio);
		domicilioDao.remove(new Long(idDomicilio));
		
	}

	@Override
	public List<Domicilio> search(String searchTerm) {
		return super.search(searchTerm, Domicilio.class);
	}

	@Override
	public Domicilio getDomicilio(String idDomicilio) {
		return domicilioDao.get(new Long(idDomicilio));
	}

	@Override
	public Domicilio saveDomicilio(Domicilio domicilio)
			throws EntityExistsException {
		try{
			return domicilioDao.saveDomicilio(domicilio);
		}
		catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("Domicilio ya existe");
		}
	}

	@Override
	public void removeDomicilio(Domicilio domicilio) {
		log.debug("removing Domicilio id: "+ domicilio.getId());
		domicilioDao.remove(domicilio);
		
	}

}
