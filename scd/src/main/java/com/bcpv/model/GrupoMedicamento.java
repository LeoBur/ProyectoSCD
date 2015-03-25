package com.bcpv.model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Leo on 23/03/2015.
 */
@Entity
@Table(name="GrupoMedicamento")
@Indexed
public class GrupoMedicamento implements Serializable{
    private Long idGrupo;
    private String nombre;

    public GrupoMedicamento(){}

    public GrupoMedicamento(String nombre) {
        this.setNombre(nombre.toUpperCase());
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id_grupo", unique = true, nullable = false)
    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Column(name = "nombre_grupo", nullable = false)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
