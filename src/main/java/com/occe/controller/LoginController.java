
package com.occe.controller;

import com.occe.model.info.Credenciales;
import com.occe.model.info.ResponseLogin;
import com.occe.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private AlumnoService alumnoService;
    
    @PostMapping("/validar")
    private ResponseLogin validarAlumno(@RequestBody Credenciales request){
                
        String email = request.getEmail();
        Integer expediente = request.getExpediente();
        
        if(!email.endsWith("@unison.mx")){                        
            return null;
        }
        
        String expedienteStr = String.valueOf(expediente);
        String expedienteCorreo = email.substring(1, email.indexOf("@"));
        
        if(!expedienteStr.equals(expedienteCorreo)){
            return null;
        }
        
        if(alumnoService.validarAlumno(expediente)){             
            return new ResponseLogin("/panel_estudiante", expediente);
        }               
        
        return null;
    }
    
}
