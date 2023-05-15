
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
    
    @GetMapping("/materias-pendientes/{expediente}-{semestre}-{plan}-{prog}")
    private List<Object[]> obtenerDatosEstadisticos(@PathVariable("expediente") Long expediente, @PathVariable("semestre") Integer semestre,@PathVariable("plan") Long plan, @PathVariable("prog") String prog){
        materiaService.crearTablaTemporal(expediente, semestre);
        return materiaService.obtenerDatosEstadisticos(plan, prog);
    }
    
    @GetMapping("/tabla-solicitudes/{programa}-{plan}")
    private void crearTablaSolicitudes(@PathVariable("programa") String programa, @PathVariable("plan") Long plan){
        String tableName = programa + "_" + plan;        
        materiaService.crearTablaSolicitudes(tableName);
    }
        
}
