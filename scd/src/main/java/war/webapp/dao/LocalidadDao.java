package war.webapp.dao;

import java.util.List;

import war.webapp.model.Localidad;

public interface LocalidadDao extends GenericDao<Localidad, Long> {
	
	Localidad loadLocalidadByNombre(String nombre);
	
	List<Localidad> getLocalidades();
	
	Localidad saveLocalidad(Localidad localidad);

}
