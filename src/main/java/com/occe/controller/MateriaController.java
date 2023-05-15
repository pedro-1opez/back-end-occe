
package com.occe.controller;

import com.occe.model.Materia;
import com.occe.model.info.MateriasPendientes;
import com.occe.service.MateriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @GetMapping("/materias-pendientes/{expediente}-{semestre}")
    private void crearTablaTemporal(@PathVariable("expediente") Long expediente, @PathVariable("semestre") Integer semestre){
        materiaService.crearTablaTemporal(expediente, semestre);
    }
    
    @GetMapping("/datos-tabla-temporal")
    private List<Object[]> obtenerDatosTablaTemporal(){
        return materiaService.obtenerDatosTablaTemporal();
    }
    
}
