
package com.occe.service;

import com.occe.model.Materia;
import com.occe.model.info.CulturestInfo;
import com.occe.model.info.MateriasPendientes;
import com.occe.model.info.MaximoMinimoMaterias;
import com.occe.model.info.Solicitud;
import com.occe.model.info.SolicitudRequest;
import com.occe.repository.MateriaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

@Service
public class MateriaService implements MateriaRepository{
    
    @Autowired
    private MateriaRepository materiaRepository;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Materia> findAll() {
        return materiaRepository.findAll();
    }   
    
    @Override
    public void crearTablaTemporal(Integer expediente, Integer semestre) {
        materiaRepository.crearTablaTemporal(expediente, semestre);
    }

    @Transactional
    public void eliminaTablaTemporal() {
        
        String sql = "DROP TEMPORARY TABLE IF EXISTS materiasAlumnoTemporal";
        
        entityManager.createNativeQuery(sql).executeUpdate();                    
    }
    
    @Override
    public List<Object[]> obtenerDatosTablaTemporal() {
        return materiaRepository.obtenerDatosTablaTemporal();
    }
    
    @Override
    public List<Object[]> obtenerDatosEstadisticos(Integer plan, String prog, Integer expediente) {
        return materiaRepository.obtenerDatosEstadisticos(plan, prog, expediente);
    }
           
    @Transactional
    public void crearTablaSolicitudes(String tableName){
        String sql = "CREATE TABLE IF NOT EXISTS "+ tableName + " (\n" +
                   "             id INT NOT NULL AUTO_INCREMENT, \n" +
                   "             expediente INT NOT NULL, \n" +
                   "             clave INT NOT NULL, \n" +
                   "             descripcion VARCHAR(255) NOT NULL, \n" +
                   "             campus VARCHAR(255) NOT NULL, \n" +
                   "             periodo INT NOT NULL, \n" +
                   "             PRIMARY KEY (id)) ENGINE=InnoDB";
        
        entityManager.createNativeQuery(sql).executeUpdate();        
    }           
    
    public boolean existenSolicitudesAlumno(String tableName, Integer expediente){
        String sql = "SELECT expediente FROM " + tableName + " WHERE expediente = :expediente";
        
        List<?> results = entityManager.createNativeQuery(sql)
                .setParameter("expediente", expediente)
                .getResultList();
        
        return !results.isEmpty();
    }
    
    @Transactional    
    public void eliminaSolicitudes(String tableName, Integer expediente){
        String sql = "DELETE FROM " + tableName + " WHERE expediente = :expediente";
        
        entityManager.createNativeQuery(sql)
                .setParameter("expediente", expediente)
                .executeUpdate();    
    }        
    
    public MaximoMinimoMaterias getMaximoMinimoMaterias(Integer expediente){
        
        String sql = "SELECT MIN(periodoMateria) AS minimo, MAX(periodoMateria) AS maximo \n" +
                     "FROM (\n" +
                     "    SELECT COUNT(periodo) AS periodoMateria \n" +
                     "    FROM inscripcion \n" +
                     "    WHERE expediente = :expediente \n" +
                     "    GROUP BY periodo) \n" +
                     "AS per";
        
        Object[] result = (Object[]) entityManager.createNativeQuery(sql)
                .setParameter("expediente", expediente)
                .getSingleResult();
        
        if(result != null){
            MaximoMinimoMaterias cantidad = new MaximoMinimoMaterias();
            cantidad.setMinimo((Long) result[0]);
            cantidad.setMaximo((Long) result[1]);
            return cantidad;
        }else{
            return null;
        }
                
    }    
      
    public List<MateriasPendientes> getMateriasPendientes(Integer plan, String prog, Integer expediente){
        List<Object[]> materias = this.obtenerDatosEstadisticos(plan, prog, expediente);
        List<MateriasPendientes> materiasPendientes = new ArrayList<>();
        
        for(Object[] materia : materias){
            String descripcion = (String) materia[0];            
            Double promedioMateria = (Double) materia[1];            
            Double indiceBajas = ((BigDecimal) materia[2]).doubleValue();
            Double porcentajeAprobacion = ((BigDecimal) materia[3]).doubleValue();            
            Long alumnosBajas = ((BigDecimal) materia[4]).longValue();            
            Long alumnosInscritos = ((BigDecimal) materia[5]).longValue();
            String estado = (String) materia[6];
            Integer creditos = (Integer) materia[7];
            Integer clave = (Integer) materia[8];
            String req = (String) materia[9];
            Integer semestre = (Integer) materia[10];
            Long intentos = (Long) materia[11];
            
            MateriasPendientes materiaPendiente = new MateriasPendientes(descripcion, promedioMateria, indiceBajas, porcentajeAprobacion, alumnosBajas, alumnosInscritos, estado, creditos, clave, req, semestre, intentos);
            
            materiasPendientes.add(materiaPendiente);
        }
        
        return materiasPendientes;
    }                
        
    public CulturestInfo getEstatusCultures(Long expediente){
        
        String sql = "SELECT DISTINCT(materia.descripcion), \n" +
                     "                inscripcion.status \n" +
                     "FROM inscripcion, materia \n" +
                     "WHERE materia.clave = inscripcion.clave \n" +
                     "AND expediente = :expediente \n" +
                     "AND materia.descripcion = 'ACTIVIDADES CULTURALES Y DEPORTIVAS' \n" +
                     "GROUP BY materia.descripcion \n" +
                     "\n" +
                     "UNION \n" +
                     "\n" +
                     "SELECT materia.descripcion, \n" +
                     "       inscripcion.status \n" +
                     "FROM inscripcion, materia \n" +
                     "WHERE materia.clave = inscripcion.clave \n" +
                     "AND expediente = :expediente \n" +
                     "AND materia.descripcion = 'PRÁCTICAS PROFESIONALES'";
        
        Object[] result = (Object[]) entityManager.createNativeQuery(sql)
                .setParameter("expediente", expediente)
                .getSingleResult();
        
        if(result != null){
            CulturestInfo info = new CulturestInfo();
            info.setDescripcion((String) result[0]);
            info.setEstatus((String) result[1]);
            return info;        
        }
        
        return null;                
    }
                
    @Transactional
    public void insertarSolicitud(SolicitudRequest request, String tableName) {
                
        String sql = "INSERT INTO " + tableName + " (expediente, clave, descripcion, campus, periodo) VALUES (:expediente, :clave, :descripcion, :campus, :periodo)";
        
        Solicitud solicitud = new Solicitud();
        
        Integer expediente = request.getExpediente();
        String campus = request.getCampus();
        Integer periodo = request.getPeriodo();
        List<Materia> materias = request.getMaterias();
        
        for(Materia materia : materias){
            solicitud.setExpediente(expediente);
            solicitud.setClave(materia.getClave());
            solicitud.setDescripcion((materia.getDescripcion()));
            solicitud.setCampus(campus);
            solicitud.setPeriodo(periodo);
            
            entityManager.createNativeQuery(sql)
                    .setParameter("expediente", solicitud.getExpediente())
                    .setParameter("clave", solicitud.getClave())
                    .setParameter("descripcion", solicitud.getDescripcion())
                    .setParameter("campus", solicitud.getCampus())
                    .setParameter("periodo", solicitud.getPeriodo())
                    .executeUpdate();
        }                
                
    }        
    
    
    public Integer getPeriodoAlumno(Integer expediente){
        
        String sql = "SELECT ultimo FROM alum_acad WHERE expediente = :expediente";
        
        Integer periodo = (Integer) entityManager.createNativeQuery(sql)
                .setParameter("expediente", expediente)
                .getSingleResult();
        
        return periodo;    
    }
    
    public List<Solicitud> getSolicitudesAlumno(String tableName, Integer expediente, Integer periodo){
            
        String sql = "SELECT expediente, clave, descripcion, campus, periodo FROM " + tableName + " WHERE expediente = :expediente AND periodo = :periodo";
    
        List<Solicitud> solicitudes = new ArrayList<>();
        List<Object[]> results = entityManager.createNativeQuery(sql)
                .setParameter("expediente", expediente)
                .setParameter("periodo", periodo)
                .getResultList();
        
        for(Object[] result : results){
            Integer resultadoExpediente = (Integer) result[0];
            Integer clave = (Integer) result[1];
            String descripcion = (String) result[2];
            String campus = (String) result[3];
            Integer resultadoPeriodo = (Integer) result[4];
        
            Solicitud solicitud = new Solicitud(resultadoExpediente, clave, descripcion, campus, resultadoPeriodo);            
            solicitudes.add(solicitud);
        }
        
        return solicitudes;
    }
    
    
    
    
    
    @Override
    public void flush() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <S extends Materia> S saveAndFlush(S entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <S extends Materia> List<S> saveAllAndFlush(Iterable<S> entities) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteAllInBatch(Iterable<Materia> entities) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteAllInBatch() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Materia getOne(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Materia getById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Materia getReferenceById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <S extends Materia> List<S> findAll(Example<S> example) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <S extends Materia> List<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <S extends Materia> List<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }   

    @Override
    public List<Materia> findAllById(Iterable<Long> ids) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <S extends Materia> S save(S entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<Materia> findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean existsById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Materia entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteAll(Iterable<? extends Materia> entities) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Materia> findAll(Sort sort) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Page<Materia> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <S extends Materia> Optional<S> findOne(Example<S> example) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <S extends Materia> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <S extends Materia> long count(Example<S> example) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <S extends Materia> boolean exists(Example<S> example) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <S extends Materia, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }                     
              
}
