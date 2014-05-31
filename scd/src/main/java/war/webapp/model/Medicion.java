package war.webapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "medicion")
@Indexed
public class Medicion implements Serializable{
	
	private static final long serialVersionUID = -4602919381022417928L;
	
	private Long id_medicion;
	private String unidad;
	private int valor;
	private DateTime f_medicion;
	private Paciente paciente;
	private static final int CONVERSION=18;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId_medicion() {
		return id_medicion;
	}
	public void setId_medicion(Long id_medicion) {
		this.id_medicion = id_medicion;
	}
	
	@Column(name = "unidad")
	@Value("mg/dl|mmol/l")
	public String getUnidad(){
		return unidad;
	}
	
	public void setUnidad(String unidad){
		this.unidad = unidad;
	}
	
	@Column(name = "valor", nullable = false, unique = false)
	@Field
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	@Column(name = "fecha_medicion", nullable = false)
	public DateTime getF_medicion() {
		return f_medicion;
	}
	
	public void setF_medicion(DateTime f_medicion) {
		this.f_medicion = f_medicion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente", nullable = false)
	public Paciente getPaciente() {
		return paciente;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	@Column(name="factor_conversion")
	public static int getConversion() {
		return CONVERSION;
	}
	
	
}
