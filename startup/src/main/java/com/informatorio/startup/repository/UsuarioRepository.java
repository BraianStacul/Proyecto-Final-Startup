package com.informatorio.startup.repository;

import com.informatorio.startup.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
    List<Usuario> findByCiudad(String ciudad);
    @Query("from Usuario where fechaCreacion >= ?1")
    List<Usuario> findByFecha(LocalDateTime fechaCreacion);

}