package com.occe.model.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MateriasPendientes {
    
    private String descripcion;
    private Double promedioMateria;
    private Double indiceBajas;
    private Double porcentajeDeAprobacion;
    private Long alumnosBajas;
    private Long alumnosInscritos;
    private String estado;
    private Integer creditos;
    private Integer clave;
    private String req;
    private Integer semestre;
    private Long intentos;
    
}
