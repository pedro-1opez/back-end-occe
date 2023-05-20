package com.occe.repository;

import com.occe.model.Inscripcion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long>{
    
    @Query(value = "SELECT i.clave FROM inscripcion i WHERE expediente = ?1 AND status = ?2", nativeQuery = true)
    List<Long> getMateriasCursando(Long expediente, String status);
    
    @Query(value = "SELECT i.clave FROM inscripcion i WHERE expediente = ?1 AND status = ?2", nativeQuery = true)
    List<Long> getMateriasAcreditadas(Long expediente, String status);
    
    @Query(value = "SELECT COUNT(DISTINCT i.periodo) FROM Inscripcion i, alum_acad aa WHERE i.expediente = ?1 AND i.prog = aa.programa", nativeQuery = true)
    Integer getSemestreCursando(Integer expediente);
    
    
}
