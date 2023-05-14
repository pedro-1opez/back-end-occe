
package com.occe.repository;

import com.occe.model.Alumno;
import com.occe.model.AlumnoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlumnoRepository extends JpaRepository<Alumno, Long>{
    
    Alumno findByExpediente(Long expediente);
    
    @Query("SELECT new com.occe.model.AlumnoInfo(a.nombre, a.programa, a.campus, a.plan) FROM Alumno a WHERE a.expediente = :expediente")
    AlumnoInfo getDatosAlumno(@Param("expediente") Long expediente);
}
