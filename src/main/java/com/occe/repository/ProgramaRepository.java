package com.occe.repository;

import com.occe.model.Programa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramaRepository extends JpaRepository<Programa, Long>{
 
    Programa getDescripcionByClave(String clave);
    
}
