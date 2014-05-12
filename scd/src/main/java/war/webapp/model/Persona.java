package war.webapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;

@Entity
@Table(name="Persona")
@Inheritance(strategy=InheritanceType.JOINED)
public class Persona {
	
	private Long id_persona;
	private int dni_persona;
	private String nombre_persona;
	private String apellido_persona;
	/**
	 * TODO
	 * private Domicilio domicilio_persona;
	 */
	private Long telefono_persona;
	
	public Persona(){
	}
	
	public Persona(int dni, String nombre, String apellido, Long telefono){
		this.dni_persona = dni;
		this.nombre_persona = nombre;
		this.apellido_persona = apellido;
		this.telefono_persona = telefono;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_persona")
	public Long getId_persona() {
		return id_persona;
	}
	
	public void setId_persona(Long id_persona) {
		this.id_persona = id_persona;
	}
	
	@Column(name = "dni_persona", nullable = false, unique = true)
	public int getDni_persona() {
		return dni_persona;
	}
	
	public void setDni_persona(int dni_persona) {
		this.dni_persona = dni_persona;
	}
	
	@Column(name = "nombre_persona")
	@Field
	public String getNombre_persona() {
		return nombre_persona;
	}
	
	public void setNombre_persona(String nombre_persona) {
		this.nombre_persona = nombre_persona;
	}
	
	@Column(name = "apellido_persona")
	@Field
	public String getApellido_persona() {
		return apellido_persona;
	}
	
	public void setApellido_persona(String apellido_persona) {
		this.apellido_persona = apellido_persona;
	}
	
	@Column(name = "telefono_persona")
	public Long getTelefono_persona() {
		return telefono_persona;
	}
	
	public void setTelefono_persona(Long telefono_persona) {
		this.telefono_persona = telefono_persona;
	}
	
}
