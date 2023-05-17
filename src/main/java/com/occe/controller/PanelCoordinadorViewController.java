package com.occe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.occe.service.ConsultarRecurso;
import org.springframework.core.io.Resource;

@Controller
@RequestMapping("/panel_coordinador")
public class PanelCoordinadorViewController {

    @GetMapping()
    public ResponseEntity<Resource> index(){
        return ConsultarRecurso.getResource("/panel_coordinador/index.html");
    }
}
