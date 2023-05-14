
package com.occe.repository;

import com.occe.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Long>{
    
    Alumno findByExpediente(Long expediente);
    
}
