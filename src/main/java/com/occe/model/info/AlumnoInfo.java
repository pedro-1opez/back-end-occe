
package com.occe.model.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoInfo {
    
    private String nombre;
    private String prog;
    private String campus;
    private Long plan;
    
}
