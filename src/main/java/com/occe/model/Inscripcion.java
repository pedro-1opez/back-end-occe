
package com.occe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inscripcion")
public class Inscripcion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long expediente;
    
    @Column(name = "depa")
    private Long departamento;
    
    @Column(name = "prog")
    private String programa;
    
    private Long plan;
    private Long clave;
    private Long creditos;
    private Long grupo;
    private String status;
    private Long ord;
    private String extra;
    private Long inscr;
    private Long bajas;
    private Long rep;
    private Long periodo;                    
    
}
