
package com.occe.repository;

import com.occe.model.AlumnoAcad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlumnoAcadRepository extends JpaRepository<AlumnoAcad, Long>{
    
    @Query(value = "SELECT ultimo FROM alum_acad WHERE expediente = ?1", nativeQuery = true)
    Long getUltimoPeriodoCursado(Long expediente);
    
    @Query("SELECT a.status, a.tipo FROM AlumnoAcad a WHERE a.expediente = :expediente")
    List<String> getEstatusAndTipoAlumno(@Param("expediente") Long expediente);       
    
}
