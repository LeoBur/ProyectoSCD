package war.webapp.dao;

import java.util.List;

import war.webapp.model.Especialista;

public interface EspecialistaDao extends PersonaDao{
	
	List<Especialista> getEspecialistaByTipo (String tipo);

}
