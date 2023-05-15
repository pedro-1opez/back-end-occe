
package com.occe.repository;

import com.occe.model.MatProg;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatProgRepository extends JpaRepository<MatProg, Long>{
    
    @Query("SELECT m.creditos FROM MatProg m \n" +
           "WHERE m.plan = :plan AND m.programa = :programa \n" +
           "AND sem < \n" +
           "    (SELECT MAX(sem) FROM MatProg m \n" +
           "     WHERE m.plan = :plan AND m.programa = :programa) \n" +
           "AND m.clave NOT IN \n" +
           "    (SELECT i.clave FROM Inscripcion i \n" +
           "     WHERE i.expediente = :expediente AND i.status = 'A') \n" +
           "AND m.clave != 119")
    List<Long> getCreditosMaterias(@Param("plan") Long plan, @Param("programa") String programa, @Param("expediente") Long expediente);    
    
    
    
}
