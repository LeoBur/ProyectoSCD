package war.webapp.dao;

import war.webapp.model.Especialista;

public interface EspecialistaDao extends PersonaDao{
	
	Especialista getEspecialistaByTipo (String tipo);

}
