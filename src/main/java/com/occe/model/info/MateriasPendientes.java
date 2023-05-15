
package com.occe.model.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MateriasPendientes {
    
    private String descripcion;
    private Long promedioMateria;
    private Long indiceBajas;
    private Long porcentajeDeAprobacion;
    private Long alumnosBajas;
    private Long alumnosInscritos;
    private String estado;
    private Long creditos;
    private String clave;
    private String req;
    
}
