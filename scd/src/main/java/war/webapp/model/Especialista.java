package war.webapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;

@Entity
@Table(name ="Especialista")
@PrimaryKeyJoinColumn(name ="id_persona")
public class Especialista extends Persona{
	
	private String tipo_esp;
	
	public Especialista(){
	}
	
	public Especialista(int dni, String nombre, String apellido, Long telefono, String tipo){
		super(dni, nombre, apellido, telefono);
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
