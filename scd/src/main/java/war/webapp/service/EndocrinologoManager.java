package war.webapp.service;

import war.webapp.dao.EndocrinologoDao;
import war.webapp.model.Endocrinologo;

public interface EndocrinologoManager extends PersonaManager{
	
	void setEndocrinologoDao(EndocrinologoDao endocrinologoDao);
	
	Endocrinologo getEndocrinologoByMatricula(Long matricula);

}
