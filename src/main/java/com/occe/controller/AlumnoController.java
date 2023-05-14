
package com.occe.controller;

import com.occe.model.Alumno;
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
    
}
