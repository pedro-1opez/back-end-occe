
package com.occe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "alumno")
public class Alumno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long expediente;
    private String nombre;
    
    @Column(name = "depto")
    private Long departamento;
    
    private String campus;
    
    @Column(name = "prog")
    private String programa;
    
    private String opcion;
    private String especialidad;
    private Long plan;
    private String status;
    private Long uciclo;
    private Long uinsc;
    private Long riesgo;
    private Long riesgoant;
    
}
