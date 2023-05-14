
package com.occe.controller;

import com.occe.model.Materia;
import com.occe.service.MateriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/materia")
public class MateriaController {
    
    @Autowired
    private MateriaService materiaService;
    
    @GetMapping("/materias")
    private ResponseEntity<List<Materia>> getAllMaterias(){
        return ResponseEntity.ok(materiaService.findAll());
    }
    
}
