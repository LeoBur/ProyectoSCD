package war.webapp.service.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import war.webapp.dao.EndocrinologoDao;
import war.webapp.model.Endocrinologo;
import war.webapp.service.EndocrinologoManager;
import war.webapp.service.EndocrinologoService;

@Service("endocrinologoManager")
@WebService(serviceName = "EndocrinologoService", endpointInterface = "war.webapp.service.EndocrinologoService")
public class EndocrinologoManagerImpl extends PersonaManagerImpl implements EndocrinologoManager, EndocrinologoService{

	private EndocrinologoDao endocrinologoDao;
	
	@Override
	public void setEndocrinologoDao(EndocrinologoDao endocrinologoDao) {
		this.dao = endocrinologoDao;
		this.endocrinologoDao = endocrinologoDao;
		
	}
	
	@Override
	public Endocrinologo getEndocrinologoByMatricula(Long matricula) {
		return endocrinologoDao.getEndocrinologoByMatricula(matricula);
	}
	
}
