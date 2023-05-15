
package com.occe.model.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MateriasPendientes {
    
    private String descripcion;
    private Long clave;
    private String estado;
    private String prog;
    private Long plan;
    
}
