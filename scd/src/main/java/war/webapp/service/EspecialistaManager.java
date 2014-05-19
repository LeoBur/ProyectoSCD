package war.webapp.service;

import java.util.List;

import war.webapp.dao.EspecialistaDao;
import war.webapp.model.Especialista;

public interface EspecialistaManager extends PersonaManager{
	
	void setEspecialistaDao(EspecialistaDao especialistaDao);
	
	List<Especialista> getEspecialistasByTipo (String tipo_esp);
	
}
