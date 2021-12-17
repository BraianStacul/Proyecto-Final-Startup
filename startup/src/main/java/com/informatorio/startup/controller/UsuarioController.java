package com.informatorio.startup.controller;

import com.informatorio.startup.entity.Usuario;
import com.informatorio.startup.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/* Inicio del Controlador */

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    //Se aplican las dependencias

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /* Acciones del Controlador */

    //Crear un usuario
    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.crear(usuario), HttpStatus.CREATED);
    }

    //Eliminar un usuario
    @DeleteMapping(value = "/{id}/eliminar")
    public Usuario eliminarUsuario(@PathVariable("id") Long id, Usuario usuario) {
        return this.usuarioService.eliminar(usuario, id);
    }

    //Modificar un usuario
    @PutMapping(value = "/{id}")
    public Usuario modificarUsuario(@PathVariable("id") Long id, @Valid @RequestBody Usuario usuario) {
        return this.usuarioService.modificar(usuario, id);
    }

    //Obtener los Usuarios
    @GetMapping
    public ResponseEntity<?> obtenerTodosLosUsuarios(@RequestParam(name = "fecha", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaCreacion,
                                                     @RequestParam(name = "ciudad", required = false) String ciudad) {
        return new ResponseEntity<>(usuarioService.todosLosUsuarios(fechaCreacion, ciudad), HttpStatus.OK);
    }

}