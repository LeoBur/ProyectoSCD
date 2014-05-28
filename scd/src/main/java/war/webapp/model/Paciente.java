package war.webapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "paciente")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="id_paciente")),
	@AttributeOverride(name="dni", column=@Column(name="dni_paciente")),
	@AttributeOverride(name="nombre", column=@Column(name="nombre_paciente")),
	@AttributeOverride(name="apellido", column=@Column(name="apellido_paciente")),
	@AttributeOverride(name="telefono", column=@Column(name="telefono_paciente")),
	@AttributeOverride(name="email", column=@Column(name="email_paciente"))
})
public class Paciente extends Persona{
	
	private static final long serialVersionUID = -6094399498007212046L;
	
	private String sexo;
	private String observaciones;
	private TipoDiabetes tipo;
	private Endocrinologo endocrinologo;
	private Set<Medicion> mediciones = new HashSet<Medicion>();
	private Set<Peso> pesos = new HashSet<Peso>();
	
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
