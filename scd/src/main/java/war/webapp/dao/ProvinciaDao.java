package war.webapp.dao;

import java.util.List;

import war.webapp.model.Provincia;

public interface ProvinciaDao extends GenericDao<Provincia, Long> {
	
	Provincia loadProvinciaByNombre(String nombre);
	
	List<Provincia> getProvincias();
	
	Provincia saveProvincia(Provincia provincia);

}
