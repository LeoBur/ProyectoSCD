package war.webapp.dao;

import javax.persistence.EntityNotFoundException;

import war.webapp.model.Domicilio;

public interface DomicilioDao extends GenericDao<Domicilio, Long>{
	
	Domicilio loadDomicilioById(Long id)throws EntityNotFoundException;
	
	Domicilio saveDomicilio(Domicilio domicilio);

}
