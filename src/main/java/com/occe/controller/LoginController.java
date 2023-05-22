
package com.occe.controller;

import com.occe.model.info.Credenciales;
import com.occe.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private String validarAlumno(@RequestBody Credenciales request, RedirectAttributes redirectAttributes){
                
        String email = request.getEmail();
        Integer expediente = request.getExpediente();
        
        if(!email.endsWith("@unison.mx")){                        
            return "redirect:/login";
        }
        
        String expedienteStr = String.valueOf(expediente);
        String expedienteCorreo = email.substring(1, email.indexOf("@"));
        
        if(!expedienteStr.equals(expedienteCorreo)){
            return "redirect:/login";
        }
        
        if(alumnoService.validarAlumno(expediente)){
            redirectAttributes.addFlashAttribute("expediente",expediente);            
            return "redirect:/panel_estudiante";
        }               
        
        return "redirect:/login";
    }
    
}
