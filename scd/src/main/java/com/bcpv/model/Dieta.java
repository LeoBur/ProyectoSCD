package com.bcpv.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Indexed;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "dieta", catalog = "scd", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id_dieta")})
@Indexed
public class Dieta implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -4809121412435221580L;

	private Long idDieta;
	private Paciente Paciente;
	private Date fechaAlta;
	private Set<DiaDieta> diasDieta = new HashSet<DiaDieta>();
	private String descripcion;

	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id_dieta", unique = true, nullable = false)
	public Long getIdDieta() {
		return idDieta;
	}
	public void setIdDieta(Long idDieta) {
		this.idDieta = idDieta;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_paciente",nullable=true)
	public Paciente getPaciente() {
		return Paciente;
	}
	public void setPaciente(Paciente paciente) {
		Paciente = paciente;
	}

	@Column(name="fecha_alta")
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @Cascade(CascadeType.ALL)
    @JoinTable(
            name = "dieta_dia_dieta",
            joinColumns = { @JoinColumn(name = "id_dieta") },
            inverseJoinColumns = { @JoinColumn(name = "idDiaDieta") })
	public Set<DiaDieta> getDiasDieta() {
		return diasDieta;
	}


    /*@ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @Cascade(CascadeType.DELETE)
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )*/

    public void setDiasDieta(Set<DiaDieta> diasDieta) {
		this.diasDieta = diasDieta;
	}
	
	@Column(name="descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion.toUpperCase();
	}

}
