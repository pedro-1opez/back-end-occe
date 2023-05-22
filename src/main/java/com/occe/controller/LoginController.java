
package com.occe.controller;

import com.occe.model.info.Credenciales;
import com.occe.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private AlumnoService alumnoService;
    
    @PostMapping("/validar")
    private ResponseEntity<?> validarAlumno(@RequestBody Credenciales request, RedirectAttributes redirectAttributes){
                
        String email = request.getEmail();
        Integer expediente = request.getExpediente();
        
        if(!email.endsWith("@unison.mx")){                        
            return ResponseEntity.badRequest().body("Invalid email");
        }
        
        String expedienteStr = String.valueOf(expediente);
        String expedienteCorreo = email.substring(1, email.indexOf("@"));
        
        if(!expedienteStr.equals(expedienteCorreo)){
            return ResponseEntity.badRequest().body("Validation failed");
        }
        
        if(alumnoService.validarAlumno(expediente)){
            redirectAttributes.addFlashAttribute("expediente",expediente);            
            return ResponseEntity.ok().body("/panel_estudiante");
        }               
        
        return ResponseEntity.badRequest().body("Validation failed");
    }
    
}
