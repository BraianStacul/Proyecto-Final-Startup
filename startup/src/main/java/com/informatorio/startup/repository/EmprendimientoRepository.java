package com.informatorio.startup.repository;

import com.informatorio.startup.entity.Emprendimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprendimientoRepository extends JpaRepository<Emprendimiento, Long> {

    List<Emprendimiento> findByOwner(Long id);
    List<Emprendimiento> findByPublicado(Boolean publicado);
    @Query("SELECT e FROM Emprendimiento e join fetch e.tags t WHERE t.nombre = :tag")
    List<Emprendimiento> findByTags_nombre(@Param("tag") String tag);

}