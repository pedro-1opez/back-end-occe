
package com.occe.model.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Solicitud {
    
    private Integer expediente;
    private Integer clave;
    private String descripcion;
    private String campus;
    private Integer periodo;
    
    
}
