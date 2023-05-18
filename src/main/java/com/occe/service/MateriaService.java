
package com.occe.service;

import com.occe.model.Materia;
import com.occe.model.info.EstatusTipoAlumno;
import com.occe.model.info.MateriasPendientes;
import com.occe.model.info.MaximoMinimoMaterias;
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
    public void crearTablaTemporal(Long expediente, Integer semestre) {
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
    public List<Object[]> obtenerDatosEstadisticos(Integer plan, String prog, Long expediente) {
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
    
    public boolean existenSolicitudesAlumno(String tableName, Long expediente){
        String sql = "SELECT * FROM " + tableName + " WHERE expediente = :expediente";
        
        List<?> results = entityManager.createNativeQuery(sql)
                .setParameter("expediente", expediente)
                .getResultList();
        
        return !results.isEmpty();
    }
    
    @Transactional    
    public void eliminaSolicitudes(String tableName, Long expediente){
        String sql = "DELETE FROM " + tableName + " WHERE expediente = :expediente";
        
        entityManager.createNativeQuery(sql)
                .setParameter("expediente", expediente)
                .executeUpdate();    
    }        
    
    public MaximoMinimoMaterias getMaximoMinimoMaterias(Long expediente){
        
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
      
    public List<MateriasPendientes> getMateriasPendientes(Integer plan, String prog, Long expediente){
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
            Integer intentos = (Integer) materia[11];
            
            MateriasPendientes materiaPendiente = new MateriasPendientes(descripcion, promedioMateria, indiceBajas, porcentajeAprobacion, alumnosBajas, alumnosInscritos, estado, creditos, clave, req, semestre, intentos);
            
            materiasPendientes.add(materiaPendiente);
        }
        
        return materiasPendientes;
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
