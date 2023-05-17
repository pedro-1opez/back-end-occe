package com.occe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.occe.service.ConsultarRecurso;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/login")
public class LoginViewController {

    @GetMapping()
    public ResponseEntity<Resource> index(){
        return ConsultarRecurso.getResource("/login/index.html");
    }
}
