package war.webapp.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;

@Entity
@Table(name ="especialista")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="id_esp")),
	@AttributeOverride(name="dni", column=@Column(name="dni_esp")),
	@AttributeOverride(name="nombre", column=@Column(name="nombre_esp")),
	@AttributeOverride(name="apellido", column=@Column(name="apellido_esp")),
	@AttributeOverride(name="telefono", column=@Column(name="telefono_esp")),
	@AttributeOverride(name="email", column=@Column(name="email_esp"))
})
public class Especialista extends Persona{
	
	private static final long serialVersionUID = 3657563589343488236L;
	
	private String tipo_esp;
	
	public Especialista(){
	}
	
	public Especialista(int dni, String nombre, String apellido, Long telefono, String email, String tipo,Domicilio domicilio){
		super(dni, nombre, apellido, telefono, email,domicilio);
		this.tipo_esp = tipo;
	}

	@Column(name = "tipo_esp")
	@Field
	public String getTipo_esp() {
		return tipo_esp;
	}

	public void setTipo_esp(String tipo_esp) {
		this.tipo_esp = tipo_esp;
	}
	
	

}
