package war.webapp.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import war.webapp.dao.ProvinciaDao;
import war.webapp.model.Provincia;

public interface ProvinciaManager extends GenericManager<Provincia, Long>{
	
	void setProvinciaDao(ProvinciaDao provinciaDao);
	   
    Provincia getProvincia(Long id_provincia);

    List<Provincia> getProvincias();

    Provincia saveProvincia(Provincia provincia) throws EntityExistsException;
    
    void removeProvincia(Provincia provincia);
    
    void removeProvincia(String id_provincia);

    List<Provincia> search(String searchTerm);

}
