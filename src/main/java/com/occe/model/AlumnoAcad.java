
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
@Table(name = "alum_acad")
public class AlumnoAcad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long expediente;
    
    @Column(name = "depto")
    private Long departamento;
    
    private String programa;
    private Long plan;
    private String cambios;
    
    @Column(name = "cred")
    private Long creditos;
    
    private String status;
    private String tipo;
    
    @Column(name = "cred_apro")
    private Long credApro;
    
    @Column(name = "prom_sem")
    private Float promSem;
    
    @Column(name = "prom_per")
    private Float promPer;
    
    private Long inicio;
    private Long ultimo;
    private Long periodo;
    private Long pro;
    private Long mats;
    private Long matacum;            
    
}
