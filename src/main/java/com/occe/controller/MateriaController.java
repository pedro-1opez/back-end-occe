
package com.occe.controller;

import com.occe.model.Materia;
import com.occe.model.info.CulturestInfo;
import com.occe.model.info.MateriasPendientes;
import com.occe.model.info.MaximoMinimoMaterias;
import com.occe.model.info.PlanProgramaAlumno;
import com.occe.model.info.Solicitud;
import com.occe.model.info.SolicitudRequest;
import com.occe.service.AlumnoService;
import com.occe.service.InscripcionService;
import com.occe.service.MateriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/materia")
public class MateriaController {
    
    @Autowired
    private MateriaService materiaService;
        
    @Autowired
    private InscripcionService inscripcionService;
    
    @Autowired
    private AlumnoService alumnoService;    
    
    @GetMapping("/materias")
    private ResponseEntity<List<Materia>> getAllMaterias(){
        return ResponseEntity.ok(materiaService.findAll());
    }
    
    @GetMapping("/materias-pendientes/{expediente}")
    private List<MateriasPendientes> obtenerDatosEstadisticos(@PathVariable("expediente") Long expediente){
        PlanProgramaAlumno planPrograma = alumnoService.getPlanPrograma(expediente);        
        Integer semestre = inscripcionService.getSemestreCursando(expediente);
        materiaService.eliminaTablaTemporal();
        materiaService.crearTablaTemporal(expediente, semestre + 1);
        return materiaService.getMateriasPendientes(planPrograma.getPlan(), planPrograma.getProg(), expediente);
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
        
    @GetMapping("/estatus-culturest/{expediente}")
    private CulturestInfo getEstatusCultures(@PathVariable("expediente") Long expediente){
        return materiaService.getEstatusCultures(expediente);
    }
    
    @PostMapping("/solicitar")
    private void insertarSolicitudes(@RequestBody SolicitudRequest request){
        Solicitud solicitud = new Solicitud();
        
        Long expediente = request.getExpediente();
        String campus = request.getCampus();
        Integer periodo = request.getPeriodo();
        List<Materia> materias = request.getMaterias();
        
        for(Materia materia : materias){
            solicitud.setExpediente(expediente);
            solicitud.setClave(materia.getClave());
            solicitud.setDescripcion((materia.getDescripcion()));
            solicitud.setCampus(campus);
            solicitud.setPeriodo(periodo);
            
            materiaService.insertarSolicitud(solicitud.getExpediente(), solicitud.getClave(), solicitud.getDescripcion(), solicitud.getCampus(), solicitud.getPeriodo());
        }                
    }
    
}
