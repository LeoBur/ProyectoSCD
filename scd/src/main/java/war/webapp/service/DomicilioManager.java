package war.webapp.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import war.webapp.dao.DomicilioDao;
import war.webapp.model.Domicilio;

public interface DomicilioManager extends GenericManager<Domicilio, Long>{
	
	void setDomicilioDao(DomicilioDao domicilioDao);
	
	Domicilio getDomicilio(Long idDomicilio);
	
	Domicilio saveDomicilio (Domicilio domicilio) throws EntityExistsException;
	
	void removeDomicilio(Domicilio domicilio);
	
	void removeDomicilio(String idDomicilio);
	
	List<Domicilio> search(String searchTerm);
}
