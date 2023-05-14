
package com.occe.controller;

import com.occe.model.Departamento;
import com.occe.service.DepartamentoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/departamento")
public class DepartamentoController {
    
    @Autowired
    private DepartamentoService departamentoService;
    
    @GetMapping("/departamentos")
    private ResponseEntity<List<Departamento>> getAllDepartamentos(){
        return ResponseEntity.ok(departamentoService.findAll());
    }
    
}
