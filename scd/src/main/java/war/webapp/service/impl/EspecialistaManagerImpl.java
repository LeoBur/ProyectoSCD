package war.webapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import war.webapp.dao.EspecialistaDao;
import war.webapp.model.Especialista;
import war.webapp.service.EspecialistaManager;
import war.webapp.service.EspecialistaService;

@Service("especialistaManager")
@WebService(serviceName = "EspecialistaService", endpointInterface = "war.webapp.service.EspecialistaService")
public class EspecialistaManagerImpl extends PersonaManagerImpl implements EspecialistaManager, EspecialistaService{

	private EspecialistaDao especialistaDao;
	
	@Override
	public void setEspecialistaDao(EspecialistaDao especialistaDao) {
		this.dao = especialistaDao;
		this.especialistaDao = especialistaDao;
		
	}
	
	@Override
	public List<Especialista> getEspecialistasByTipo(String tipo_esp) {
		if (especialistaDao!=null){
			return especialistaDao.getEspecialistaByTipo(tipo_esp);
		}
		return new ArrayList<Especialista>();
	}

}
