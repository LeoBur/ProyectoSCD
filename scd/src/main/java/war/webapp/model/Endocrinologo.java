package war.webapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name ="Endocrinologo")
@PrimaryKeyJoinColumn(name = "id_persona")
public class Endocrinologo extends Persona{
	
	private Long matricula;
    
	public Endocrinologo() {
	}
	
	public Endocrinologo(int dni_persona, String nombre_persona, String apellido_persona, Long telefono_persona, Long matricula){
		super(dni_persona, nombre_persona, apellido_persona, telefono_persona);
		this.matricula = matricula;
		
	}
	
	@Column(name ="matricula_endo")
	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}
	
	

}
