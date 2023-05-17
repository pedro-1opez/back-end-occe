package com.occe.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public class ConsultarRecurso {
    
    public static ResponseEntity<Resource> getResource(String path){
        try{
            File file = new File(System.getProperty("user.dir")+"/src/main/resources/static"+path);
            System.out.println(file.getAbsolutePath());
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=" + file.getName());
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("text/html"))
                    .body(resource);
    
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
