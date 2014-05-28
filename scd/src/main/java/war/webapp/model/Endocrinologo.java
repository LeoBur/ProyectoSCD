package war.webapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name ="endocrinologo")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="id_endo")),
	@AttributeOverride(name="dni", column=@Column(name="dni_endo")),
	@AttributeOverride(name="nombre", column=@Column(name="nombre_endo")),
	@AttributeOverride(name="apellido", column=@Column(name="apellido_endo")),
	@AttributeOverride(name="telefono", column=@Column(name="telefono_endo")),
	@AttributeOverride(name="email", column=@Column(name="email_endo"))
})
public class Endocrinologo extends Persona {
	
	private static final long serialVersionUID = -1274195813657855646L;
	
	private Long matricula;
	private Set<Paciente> pacientes = new HashSet<Paciente>();
    
	public Endocrinologo() {
	}
	
	public Endocrinologo(int dni, String nombre, String apellido, Long telefono, String email, Long matricula,Domicilio domicilio){
		super(dni, nombre, apellido, telefono, email,domicilio);
		this.matricula = matricula;
		
	}
	
	@Column(name ="matricula_endo")
	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public Set<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(Set<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	
	

}
