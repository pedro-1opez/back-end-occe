
package com.occe.controller;

import com.occe.service.MatProgService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mat-prog")
public class MatProgController {
    
    @Autowired
    private MatProgService matProgService;
    
    @GetMapping("/creditos-materia/{plan}-{programa}-{expediente}")
    private List<Long> getCreditosMateria(@PathVariable("plan") Long plan, @PathVariable("programa") String programa, @PathVariable("expediente") Long expediente){
        return matProgService.getCreditosMaterias(plan, programa, expediente);
    }
    
}
