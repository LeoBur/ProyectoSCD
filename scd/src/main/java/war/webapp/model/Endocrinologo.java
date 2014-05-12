package war.webapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name ="Endocrinologo")
@PrimaryKeyJoinColumn(name = "id_persona")
public class Endocrinologo extends Persona {
	
	private static final long serialVersionUID = -1274195813657855646L;
	
	private Long matricula;
    
	public Endocrinologo() {
	}
	
	public Endocrinologo(int dni, String nombre, String apellido, Long telefono, String email, Long matricula){
		super(dni, nombre, apellido, telefono, email);
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
