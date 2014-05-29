package war.webapp.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import war.webapp.model.Endocrinologo;

public interface EndocrinologoDao extends GenericDao<Endocrinologo, Long>{
	
	Endocrinologo loadEndocrinologoByDNI(Long dni) throws EntityNotFoundException;

    List<Endocrinologo> getEndocrinologos();
    
    Endocrinologo saveEndocrinologo(Endocrinologo endocrinologo);
	
	Endocrinologo getEndocrinologoByMatricula (Long matricula);

}
