package war.webapp.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.search.annotations.Field;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Email;

@Entity
@Table(name = "paciente")
public class Paciente implements Serializable{
	
	private static final long serialVersionUID = -6094399498007212046L;
	
	private Long id;
	private int dni;
	private String nombre;
	private String apellido;
	private Domicilio domicilio;
	private Long telefono;
	private String email;
	
	private String sexo;
	private String observaciones;
	private TipoDiabetes tipo;
	private Endocrinologo endocrinologo;
	private Set<Medicion> mediciones = new HashSet<Medicion>();
	private Set<Peso> pesos = new HashSet<Peso>();
	
	public Paciente(){
	}
	
	public Paciente(int dni, String nombre, String apellido, Long telefono, String email, Domicilio domicilio, String sexo, 
			String obs, TipoDiabetes tipo, Endocrinologo endo, Set<Medicion> medicion, Set<Peso> peso){
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.domicilio=domicilio;
		this.sexo = sexo;
		this.observaciones = obs;
		this.endocrinologo = endo;
		this.tipo = tipo;
		this.mediciones = medicion;
		this.pesos = peso;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idDomicilio", nullable = false)
	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	@Column(name = "sexo")
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getObservaciones() {
		return observaciones;
	}
	
	@Column(name = "observ")
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo", nullable = false)
	public TipoDiabetes getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoDiabetes tipo) {
		this.tipo = tipo;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_endo", nullable = false)
	public Endocrinologo getEndocrinologo() {
		return endocrinologo;
	}
	
	public void setEndocrinologo(Endocrinologo endocrinologo) {
		this.endocrinologo = endocrinologo;
	}
	
	@OneToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public Set<Medicion> getMediciones() {
		return this.mediciones;
	}

	public void setMediciones(Set<Medicion> mediciones) {
		this.mediciones = mediciones;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public Set<Peso> getPesos() {
		return pesos;
	}

	public void setPesos(Set<Peso> pesos) {
		this.pesos = pesos;
	}
}
