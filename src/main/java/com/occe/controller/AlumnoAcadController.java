
package com.occe.controller;

import com.occe.model.info.CreditosInfo;
import com.occe.model.info.EstatusTipoAlumno;
import com.occe.model.info.NombreEstatusAlumno;
import java.util.List;
import com.occe.service.AlumnoAcadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alumno-acad")
public class AlumnoAcadController {
    
    @Autowired
    private AlumnoAcadService alumnoAcadService;
    
    @GetMapping("/ultimo-periodo/{expediente}")
    private Long getUltimoPeriodoCursado(@PathVariable("expediente") Long expediente){
        return alumnoAcadService.getUltimoPeriodoCursado(expediente);
    }
    
    @GetMapping("/estatus-tipo/{expediente}")
    private EstatusTipoAlumno getEstatusAndTipoAlumno(@PathVariable("expediente") Long expediente){
        return alumnoAcadService.getEstatusAndTipoAlumno(expediente);
    }
    
    @GetMapping("/creditos/{expediente}")
    private CreditosInfo getCreditosNecesariosCreditosCursados(@PathVariable("expediente") Long expediente){
        return alumnoAcadService.getCreditosNecesariosCreditosCursados(expediente);
    }
    
    @GetMapping("/login/{expediente}")
    private NombreEstatusAlumno getNombreStatusAlumno(@PathVariable("expediente") Long expediente){
        return alumnoAcadService.getNombreStatusAlumno(expediente);
    }
    
}
