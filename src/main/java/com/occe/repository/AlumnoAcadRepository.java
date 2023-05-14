
package com.occe.repository;

import com.occe.model.AlumnoAcad;
import com.occe.model.info.CreditosInfo;
import com.occe.model.info.EstatusTipoAlumno;
import com.occe.model.info.NombreEstatusAlumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlumnoAcadRepository extends JpaRepository<AlumnoAcad, Long>{
    
    @Query(value = "SELECT ultimo FROM alum_acad WHERE expediente = ?1", nativeQuery = true)
    Long getUltimoPeriodoCursado(Long expediente);
    
    @Query("SELECT new com.occe.model.info.EstatusTipoAlumno(a.status, a.tipo) FROM AlumnoAcad a WHERE a.expediente = :expediente")
    EstatusTipoAlumno getEstatusAndTipoAlumno(@Param("expediente") Long expediente);               
    
    @Query("SELECT new com.occe.model.info.CreditosInfo(e.creditos, e.credApro) FROM AlumnoAcad e WHERE e.expediente = :expediente")
    CreditosInfo getCreditosNecesariosCreditosCursados(@Param("expediente") Long expediente);
    
    @Query("SELECT new com.occe.model.info.NombreEstatusAlumno(a.nombre, aa.status) FROM Alumno a, AlumnoAcad aa WHERE a.expediente = aa.expediente AND a.expediente = :expediente AND (aa.status = 'A' OR aa.status = 'B38')")
    NombreEstatusAlumno getNombreStatusAlumno(@Param("expediente") Long expediente);
    
}
