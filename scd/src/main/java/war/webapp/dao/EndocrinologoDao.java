package war.webapp.dao;

import war.webapp.model.Endocrinologo;

public interface EndocrinologoDao extends PersonaDao{
	
	Endocrinologo getEndocrinologoByMatricula (Long matricula);

}
