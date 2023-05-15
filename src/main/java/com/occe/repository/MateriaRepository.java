
package com.occe.repository;

import com.occe.model.Materia;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MateriaRepository extends JpaRepository<Materia, Long>{
       
    @Modifying
    @Transactional
    @Query(value = "CREATE TEMPORARY TABLE IF NOT EXISTS materiasAlumnoTemporal AS \n" +
           "       SELECT m.descripcion, \n" +
           "              m.clave, \n" +
           "              'PC' AS estado, \n" +
           "              i.prog, \n" +
           "              i.plan \n" +
           "       FROM materia m, mat_prog mp, \n" +
           "       (SELECT * FROM inscripcion i3 WHERE i3.expediente = :expediente) AS i \n" +
           "               WHERE mp.sem <= :semestre \n" +
           "               AND m.clave = mp.clave \n" +
           "               AND NOT EXISTS (SELECT i2.clave, i2.status \n" +
           "                               FROM inscripcion i2 WHERE i2.expediente = :expediente AND mp.clave = i2.clave)\n" +
           "                                  AND mp.plan = i.plan \n" +
           "                                  AND mp.programa = i.prog \n" +
           "                                  AND mp.clave != 119 \n" +
           "                                  AND mp.clave != 660 \n" +
           "                                  AND mp.clave != 733 \n" +
           "                                  AND mp.clave != 19006 \n" +
           "                                  AND mp.clave != 81 \n" +
           "                                  AND m.descripcion != 'PRACTICAS PROFESIONALES'\n" +
           "                               GROUP BY m.descripcion\n" +
           "                               \n" +
           "UNION\n" +
           "\n" +
           "       SELECT m.descripcion, \n" +
           "              m.clave, \n" +
           "              (CASE WHEN(i4.clave=m.clave) \n" +
           "                    THEN i4.status ELSE 'PC' END), i.prog, i.plan\n" +
           "                    FROM materia m, mat_prog mp, \n" +
           "       (SELECT * FROM inscripcion i3 WHERE i3.expediente = :expediente) AS i, \n" +
           "       (SELECT i3.clave, i3.status FROM inscripcion i3 WHERE i3.expediente = :expediente) AS i4 \n" +
           "              WHERE mp.sem <= :semestre \n" +
           "              AND m.clave = mp.clave \n" +
           "              AND i4.clave = mp.clave\n" +
           "       AND NOT EXISTS (SELECT i2.clave, i2.status \n" +
           "                          FROM inscripcion i2 \n" +
           "                          WHERE i2.expediente = :expediente\n" +
           "                          AND (i2.status = 'A' OR i2.status = 'C')\n" +
           "		                  AND mp.clave = i2.clave)\n" +
           "                          AND mp.plan = i.plan \n" +
           "                          AND mp.programa = i.prog \n" +
           "                          AND mp.clave != 119 \n" +
           "                          AND mp.clave != 660 \n" +
           "                          AND mp.clave != 733 \n" +
           "                          AND mp.clave != 19006 \n" +
           "                          AND mp.clave != 81 \n" +
           "                          AND m.descripcion != 'PRACTICAS PROFESIONALES' \n" +
           "               GROUP BY m.descripcion", nativeQuery = true)
    void crearTablaTemporal(@Param("expediente") Long expediente, @Param("semestre") Integer semestre);
    
    @Query(value = "SELECT * FROM materiasAlumnoTemporal", nativeQuery = true)
    List<Object[]> obtenerDatosTablaTemporal();
    
}
