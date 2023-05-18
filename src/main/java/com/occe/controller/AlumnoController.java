
package com.occe.controller;

import com.occe.model.Alumno;
import com.occe.model.info.AlumnoDepartamento;
import com.occe.model.info.AlumnoInfo;
import com.occe.model.info.PlanProgramaAlumno;
import com.occe.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alumno")
public class AlumnoController {
    
    @Autowired
    private AlumnoService alumnoService;
    
    @GetMapping("/{expediente}")
    private Alumno getAlumnoByExpediente(@PathVariable("expediente") Long expediente){
        return alumnoService.findByExpediente(expediente);
    }
    
    @GetMapping("/datos-alumno/{expediente}")
    private AlumnoInfo getDatosAlumno(@PathVariable("expediente") Long expediente){
        return alumnoService.getInformacionAlumno(expediente);
    }
    
    @GetMapping("/alumno-departamento/{expediente}")
    private AlumnoDepartamento getAlumnoDepartamento(@PathVariable("expediente") Long expediente){
        return alumnoService.getDepartamentoAlumno(expediente);
    }
    
    @GetMapping("/plan-programa/{expediente}")
    private PlanProgramaAlumno getPlanProgramaAlumno(@PathVariable("expediente") Long expediente){
        return alumnoService.getPlanPrograma(expediente);
    }
    
}
