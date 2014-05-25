package war.webapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.search.annotations.Field;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Email;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Persona implements Serializable{
	
	private static final long serialVersionUID = 707799520417936087L;
	
	private Long id;
	private int dni;
	private String nombre;
	private String apellido;
	/**
	 * TODO
	 * private Domicilio domicilio_persona;
	 */
	private Long telefono;
	private String email;

	public Persona(){
	}
	
	public Persona(int dni, String nombre, String apellido, Long telefono, String email){
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "dni", nullable = false, unique = true)
	public int getDni() {
		return dni;
	}
	
	public void setDni(int dni) {
		this.dni = dni;
	}
	
	@Column(name = "nombre")
	@Field
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name = "apellido")
	@Field
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	@Column(name = "telefono")
	public Long getTelefono() {
		return telefono;
	}
	
	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}
	
	@Column(name = "email")
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
