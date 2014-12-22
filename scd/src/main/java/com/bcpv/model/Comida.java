package com.bcpv.model;

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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Indexed;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "comida", catalog = "scd", uniqueConstraints = {
        @UniqueConstraint(columnNames = "idComida")})
public class Comida implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 7379603373775505732L;

	private Long idComida;
	private String cantidad;
	private Alimento alimento;
	private String observaciones;
    private Set<MomentoDia> momentos = new HashSet<MomentoDia>(0);


    @Id
    @GeneratedValue(strategy = IDENTITY)
	@Column(name="idComida", unique = true, nullable = false)
	public Long getIdComida() {
		return idComida;
	}
	public void setIdComida(Long idComida) {
		this.idComida = idComida;
	}

	@Column(name="cantidad",nullable=false)
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad.toUpperCase();
	}
	@ManyToOne(fetch=FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
	@JoinColumn(name="idAlimento")
	public Alimento getAlimento() {
		return alimento;
	}
	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}
	@Column(name="observaciones")
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones.toUpperCase();
	}

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "comidas")
    public Set<MomentoDia> getMomentos() {
        return momentos;
    }

    public void setMomentos(Set<MomentoDia> momentos) {
        this.momentos = momentos;
    }
}
