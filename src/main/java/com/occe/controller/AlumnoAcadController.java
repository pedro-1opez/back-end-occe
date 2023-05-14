
package com.occe.controller;

import java.util.List;
import com.occe.service.AlumnoAcadService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private List<String> getEstatusAndTipoAlumno(@PathVariable("expediente") Long expediente){
        return alumnoAcadService.getEstatusAndTipoAlumno(expediente);
    }        
    
}
