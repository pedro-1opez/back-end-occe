
package com.occe.controller;

import com.occe.model.Materia;
import com.occe.model.info.MateriasPendientes;
import com.occe.model.info.MaximoMinimoMaterias;
import com.occe.service.InscripcionService;
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
        
    @Autowired
    private InscripcionService inscripcionService;
    
    
    @GetMapping("/materias")
    private ResponseEntity<List<Materia>> getAllMaterias(){
        return ResponseEntity.ok(materiaService.findAll());
    }
    
    @GetMapping("/materias-pendientes/{expediente}-{plan}-{prog}")
    private List<MateriasPendientes> obtenerDatosEstadisticos(@PathVariable("expediente") Long expediente,@PathVariable("plan") Long plan, @PathVariable("prog") String prog){
        Integer semestre = inscripcionService.getSemestreCursando(expediente);
        materiaService.crearTablaTemporal(expediente, semestre + 1);
        return materiaService.getMateriasPendientes(plan, prog);
    }
    
    @GetMapping("/crear-tabla-solicitudes/{programa}-{plan}")
    private void crearTablaSolicitudes(@PathVariable("programa") String programa, @PathVariable("plan") Long plan){
        String tableName = programa + "_" + plan;        
        materiaService.crearTablaSolicitudes(tableName);
    }
    
    @GetMapping("/hay-solicitudes/{programa}-{plan}-{expediente}")
    private boolean haySolicitudesAlumno(@PathVariable("programa") String programa, @PathVariable("plan") Long plan, @PathVariable("expediente") Long expediente){
        String tableName = programa + "_" + plan;
        return materiaService.existenSolicitudesAlumno(tableName, expediente);
    }
    
    @GetMapping("/elimina-solicitudes/{programa}-{plan}-{expediente}")
    private void eliminaSolicitudesAlumno(@PathVariable("programa") String programa, @PathVariable("plan") Long plan, @PathVariable("expediente") Long expediente){
        String tableName = programa + "_" + plan;
        materiaService.eliminaSolicitudes(tableName, expediente);
    }
    
    @GetMapping("/maximo-minimo-materias/{expediente}")
    private MaximoMinimoMaterias getMaximoMinimoMaterias(@PathVariable("expediente") Long expediente){
        return materiaService.getMaximoMinimoMaterias(expediente);
    }
        
}
