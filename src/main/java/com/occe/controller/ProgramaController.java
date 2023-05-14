
package com.occe.controller;

import com.occe.model.Programa;
import com.occe.service.ProgramaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/programa")
public class ProgramaController {
    
    @Autowired
    private ProgramaService programaService;
    
    @GetMapping("/programas")
    private ResponseEntity<List<Programa>> getAllProgramas(){
        return ResponseEntity.ok(programaService.findAll());
    }
    
    @GetMapping("/{clave}")
    private Programa getDescripcionByClave(@PathVariable("clave") String clave){
        return programaService.getDescripcionByClave(clave);
    }
    
}
