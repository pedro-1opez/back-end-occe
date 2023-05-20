
package com.occe.model.info;

import com.occe.model.Materia;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudRequest {
    
    private Integer expediente;
    private List<Materia> materias;
    private String campus;
    private Integer periodo;
    
}
