package com.informatorio.startup.service;

import com.informatorio.startup.entity.Emprendimiento;
import com.informatorio.startup.entity.Etiqueta;
import com.informatorio.startup.entity.Usuario;
import com.informatorio.startup.repository.EmprendimientoRepository;
import com.informatorio.startup.repository.EtiquetaRepository;
import com.informatorio.startup.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
/*
------------
INICIO DEL SERVICIO
------------
 */
@Service
public class EmprendimientoService {
    private final EmprendimientoRepository emprendimientoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EtiquetaRepository etiquetaRepository;

    //SE APLICAN LAS DEPENDENCIAS
    @Autowired
    public EmprendimientoService(EmprendimientoRepository emprendimientoRepository, UsuarioRepository usuarioRepository, EtiquetaRepository etiquetaRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.usuarioRepository = usuarioRepository;
        this.etiquetaRepository = etiquetaRepository;
    }

    /*
    ------------
    ACCIONES DEL SERVICIO
    ------------
     */

    //CREAR EMPRENDIMIENTO
    public Emprendimiento crear(Emprendimiento emprendimiento, Long usuarioId) {
        Usuario usuario = usuarioRepository.getById(usuarioId);
        emprendimiento.setOwner(usuario);
        return emprendimientoRepository.save(emprendimiento);
    }

    //ELIMINAR EMPRENDIMIENTO
    public Emprendimiento eliminar(Emprendimiento emprendimiento, Long id) {
        Emprendimiento emprendimientoEliminado = emprendimientoRepository.getById(id);
        emprendimientoEliminado.setActivo(false);
        return emprendimientoRepository.save(emprendimientoEliminado);
    }

    //MODIFICAR EMPRENDIMIENTO
    public Emprendimiento modificar(Emprendimiento emprendimiento, Long id) {
        Emprendimiento emprendimientoModificado = emprendimientoRepository.getById(id);

        if (!emprendimiento.getNombre().trim().isEmpty()) {
            emprendimientoModificado.setNombre(emprendimiento.getNombre()); }

        if (!emprendimiento.getDescripcion().trim().isEmpty()) {
            emprendimientoModificado.setDescripcion(emprendimiento.getDescripcion()); }

        if (!emprendimiento.getContenido().trim().isEmpty()) {
            emprendimientoModificado.setContenido(emprendimiento.getContenido()); }

        if (emprendimiento.getObjetivo() != null && emprendimiento.getObjetivo() > 0) {
            emprendimientoModificado.setObjetivo(emprendimiento.getObjetivo()); }

        if (!emprendimiento.getPublicado()) {
            emprendimientoModificado.setPublicado(false); }

        if (emprendimiento.getPublicado()) {
            emprendimientoModificado.setPublicado(true); }

        if (emprendimiento.getUrl() != null) {
            emprendimientoModificado.setUrl(emprendimiento.getUrl()); }

        if (emprendimiento.getTags() != null) {
            emprendimientoModificado.setTags(emprendimiento.getTags()); }

        return emprendimientoRepository.save(emprendimientoModificado);
    }

    //OBTENER LOS EMPRENDIMIENTOS
    public List<Emprendimiento> obtenerTodos(String nombre) {
        if (nombre != null) {Etiqueta tag = etiquetaRepository.findByNombre(nombre);
            return tag.getEmprendimientos();
        } else { return emprendimientoRepository.findAll(); }
    }

    public List<Emprendimiento> obtenerPorTag(String nombre){
        return emprendimientoRepository.findByTags_nombre(nombre);
    }

    //OBTENER LOS NO PUBLICADOS
    public Stream<Emprendimiento> obtenerNoPublicados() {
        return emprendimientoRepository.findAll().stream().filter(Predicate.not(Emprendimiento::getPublicado));
    }
}


