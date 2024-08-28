/**
 * Entidad de incidencias
 */
package com.example.PruebaAuthSpring.Persistencia.Entidades;

import com.example.PruebaAuthSpring.Incidencias.EstadoIncidencia;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author char_
 */
@Getter
@Setter
@Entity
public class Incidencias {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String fechaInicio;
    private String fechaFin;
    @Column(nullable = false)
    private String solicitante;
    private String oficina;
    private String servicio;
    private String descripcion;
    private String comentarios;
    private String ubicacion;// TA o TB
    private String planta;
    private String departamento;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoIncidencia estado;// PENDIENTE - EN CURSO - FINALIZADA
    @Column(nullable = false)
    private String idUsuario;//Nombre de usuario para el listado de incidencias

}
