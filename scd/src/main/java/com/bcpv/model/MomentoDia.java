package com.bcpv.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "momento_dia", catalog = "scd", uniqueConstraints = {
        @UniqueConstraint(columnNames = "idMomentoD")})
public class MomentoDia implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 6002754156113557807L;

	public enum MomentosDia {DESAYUNO,MEDIA_MANIANA,ALMUERZO,MEDIA_TARDE,CENA,ANTES_DE_ACOSTARSE }

	private Long idMomentoD;
	private MomentosDia nombre;
	private Set<Comida> comidas = new HashSet<Comida>();
    private Set<DiaDieta> diaDietas = new HashSet<DiaDieta>(0);


    @Id
    @GeneratedValue(strategy = IDENTITY)
	@Column(name="idMomentoD", unique = true, nullable = false)
	public Long getIdMomentoD() {
		return idMomentoD;
	}
	public void setIdMomentoD(Long idMomentoD) {
		this.idMomentoD = idMomentoD;
	}

	@Column(name="nombre_momento")
	public MomentosDia getNombre() {
		return nombre;
	}
	public void setNombre(MomentosDia nombre) {
		this.nombre = nombre;
	}

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinTable(name = "momento_dia_comidas",
            joinColumns = { @JoinColumn(name = "idMomentoD") },
            inverseJoinColumns = { @JoinColumn(name = "idComida") })
	public Set<Comida> getComidas() {
		return comidas;
	}
	public void setComidas(Set<Comida> comidas) {
		this.comidas = comidas;
	}

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "momentosDia")
    public Set<DiaDieta> getDiaDietas() {
        return diaDietas;
    }

    public void setDiaDietas(Set<DiaDieta> diaDietas) {
        this.diaDietas = diaDietas;
    }
}
