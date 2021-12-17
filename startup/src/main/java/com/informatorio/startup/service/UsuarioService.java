package com.informatorio.startup.service;

import com.informatorio.startup.entity.Usuario;
import com.informatorio.startup.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
/*
------------
INICIO DEL SERVICIO
------------
 */
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    //SE APLICAN LAS DEPENDENCIAS
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    /*
    ------------
    ACCIONES DEL SERVICIO
    ------------
     */
    //CREAR USUARIO
    public Usuario crear(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    //ELIMINAR USUARIO
    public Usuario eliminar(Usuario usuario, Long id){
        Usuario usuarioEliminado = usuarioRepository.getById(id);
        usuarioEliminado.setActivo(false);
        return usuarioRepository.save(usuarioEliminado);
    }

    //MODIFICAR USUARIO
    public Usuario modificar(Usuario usuario, Long id) {
        Usuario usuarioModificado = usuarioRepository.getById(id);
        if (!usuario.getNombre().trim().isEmpty()) {
            usuarioModificado.setNombre(usuario.getNombre());
        }
        if (!usuario.getApellido().trim().isEmpty()) {
            usuarioModificado.setApellido(usuario.getApellido());
        }
        if (!usuario.getEmail().trim().isEmpty()) {
            usuarioModificado.setEmail(usuario.getEmail());
        }
        if (!usuario.getPassword().trim().isEmpty()) {
            usuarioModificado.setPassword(usuario.getPassword());
        }
        if (!usuario.getCiudad().trim().isEmpty()) {
            usuarioModificado.setCiudad(usuario.getCiudad());
        }
        if (!usuario.getProvincia().trim().isEmpty()) {
            usuarioModificado.setProvincia(usuario.getProvincia());
        }
        if (!usuario.getPais().trim().isEmpty()) {
            usuarioModificado.setPais(usuario.getPais());
        }
        if (usuario.getRoles() != null) {
            usuarioModificado.setRoles(usuario.getRoles());
        }

        return usuarioRepository.save(usuarioModificado);
    }

    //OBTENER TODOS LOS USUARIOS
    public List<Usuario> todosLosUsuarios(LocalDateTime fechaCreacion, String ciudad) {
        if (fechaCreacion != null) {
            return usuarioRepository.findByFecha(fechaCreacion);
        }
        else if (ciudad != null) {
            return usuarioRepository.findByCiudad(ciudad);
        }
        else {
            return usuarioRepository.findAll();
        }
    }
}