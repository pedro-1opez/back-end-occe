
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
    void crearTablaTemporal(@Param("expediente") Integer expediente, @Param("semestre") Integer semestre);
            
    @Query(value = "SELECT * FROM materiasAlumnoTemporal", nativeQuery = true)
    List<Object[]> obtenerDatosTablaTemporal();
    
    @Query(value = "SELECT materia.descripcion AS descripcion, \n" +
                   "       ROUND(SUM(CASE WHEN (inscripcion.ord > 0 OR inscripcion.ord IS NOT NULL) AND inscripcion.plan = :plan\n" +
                   "             AND inscripcion.prog = :prog \n" +
                   "                 THEN inscripcion.ord \n" +
                   "                 ELSE (CASE WHEN (inscripcion.extra > 0 OR inscripcion.extra IS NOT NULL) \n" +
                   "             AND inscripcion.plan = :plan \n" +
                   "             AND inscripcion.prog = :prog \n" +
                   "                 THEN inscripcion.extra END) END) / SUM(CASE WHEN (inscripcion.bajas = 0 OR inscripcion.bajas IS NULL) \n" +
                   "             AND inscripcion.plan = :plan \n" +
                   "             AND inscripcion.prog = :prog \n" +
                   "                 THEN 1 ELSE 0 END), 2) AS promedioMateria,\n" +
                   "       ROUND((SUM(CASE WHEN (inscripcion.bajas > 0 AND inscripcion.bajas IS NOT NULL) \n" +
                   "             AND inscripcion.plan = :plan \n" +
                   "             AND inscripcion.prog = :prog \n" +
                   "                 THEN 1 ELSE 0 END) / COUNT(CASE WHEN (inscripcion.bajas >= 0 OR inscripcion.bajas IS NOT NULL) \n" +
                   "             AND inscripcion.plan = :plan \n" +
                   "             AND inscripcion.prog = :prog \n" +
                   "                 THEN 1 ELSE 0 END))*100, 2) AS indiceBajas,\n" +
                   "       ROUND((SUM(CASE WHEN (inscripcion.ord >= 60 OR inscripcion.extra >= 60) \n" +
                   "             AND inscripcion.status = 'A' \n" +
                   "             AND inscripcion.plan = :plan \n" +
                   "             AND inscripcion.prog = :prog \n" +
                   "                 THEN 1 ELSE 0 END) / SUM(CASE WHEN (inscripcion.status = 'A' OR inscripcion.status = 'R') \n" +
                   "             AND inscripcion.plan = :plan \n" +
                   "             AND inscripcion.prog = :prog THEN 1 ELSE 0 END))*100, 2) AS porcentajeDeAprobacion,\n" +
                   "       SUM(CASE WHEN (inscripcion.bajas > 0 AND inscripcion.bajas IS NOT NULL) \n" +
                   "             AND inscripcion.plan = :plan \n" +
                   "             AND inscripcion.prog = :prog \n" +
                   "                 THEN 1 ELSE 0 END) AS alumnosBajas,\n" +
                   "       SUM(CASE WHEN (inscripcion.bajas = 0 OR inscripcion.bajas IS NULL) \n" +
                   "             AND inscripcion.plan = :plan \n" +
                   "             AND inscripcion.prog = :prog \n" +
                   "                 THEN 1 ELSE 0 END) AS alumnosInscritos, \n" +
                   "       materiasAlumnoTemporal.estado AS estado, \n" +
                   "       mat_prog.creditos as creditos, \n" +
                   "       mat_prog.clave as clave,\n" +
                   "       REGEXP_REPLACE(REPLACE (REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(req, ':', ''), 'O  ', ''), 'e', ''), 'y', ''),',',''),'/',' '),'Inscribir o Cursar', 'Cursar' ),' +', ' ') AS req,\n" +
                   "           mat_prog.sem as semestre," +
                   "           COALESCE(inscripcion2.inscr, 0) AS intentos" +
                   "                FROM inscripcion " +
                   "                JOIN materia " +
                   "                    ON inscripcion.clave = materia.clave " +
                   "                JOIN mat_prog " +
                   "                    ON materia.clave = mat_prog.clave " +
                   "                JOIN materiasAlumnoTemporal " +
                   "                    ON materia.clave = materiasAlumnoTemporal.clave " +
                   "                LEFT JOIN " +
                   "      inscripcion AS inscripcion2 ON inscripcion2.clave = mat_prog.clave " +
                   "            AND inscripcion2.expediente = :expediente" +
                   "      WHERE mat_prog.programa = :prog" +
                   "      AND mat_prog.plan = :plan " +
                   "      GROUP BY materia.descripcion, mat_prog.req, materia.clave", nativeQuery = true)
    List<Object[]> obtenerDatosEstadisticos(@Param("plan") Integer plan, @Param("prog") String prog, @Param("expediente") Integer expediente);        
    
}
