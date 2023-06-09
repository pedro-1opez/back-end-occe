
package com.occe.controller;

import com.occe.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/inscripcion")
public class InscripcionController {
    
    @Autowired
    private InscripcionService inscripcionService;
    
    @GetMapping("/cursando/{expediente}")
    private List<Long> getMateriasCursando(@PathVariable("expediente") Integer expediente){
        return inscripcionService.getMateriasCursando(expediente, "C");
    }
    
    @GetMapping("/acreditadas/{expediente}")
    private List<Long> getMateriasAcreditadas(@PathVariable("expediente") Integer expediente){
        return inscripcionService.getMateriasAcreditadas(expediente, "A");
    }
    
    @GetMapping("/semestre/{expediente}")
    private Integer getSemestreCursando(@PathVariable("expediente") Integer expediente){
        return inscripcionService.getSemestreCursando(expediente);
    }
            
}
