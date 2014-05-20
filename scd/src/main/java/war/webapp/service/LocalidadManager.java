package war.webapp.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import war.webapp.dao.LocalidadDao;
import war.webapp.model.Localidad;

public interface LocalidadManager extends GenericManager<Localidad, Long>{
	
	void setLocalidadDao(LocalidadDao localidadDao);
	   
    Localidad getLocalidad(Long id_localidad);

    List<Localidad> getLocalidades();

    Localidad saveLocalidad(Localidad localidad) throws EntityExistsException;
    
    void removeLocalidad(Localidad localidad);
    
    void removeLocalidad(String id);

    List<Localidad> search(String searchTerm);

}
