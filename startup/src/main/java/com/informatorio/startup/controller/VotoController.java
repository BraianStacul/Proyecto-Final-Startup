package com.informatorio.startup.controller;

import com.informatorio.startup.dto.VotoDTO;
import com.informatorio.startup.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/* Inicio del Controlador */
@RestController
@RequestMapping(value = "/votos")
public class VotoController {

    private final VotoService votoService;

    //Se aplican las dependencias
    @Autowired
    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    /* Acciones del Controlador */

    //Crear un voto
    @PostMapping
    public ResponseEntity<?> crearVoto(@Valid @RequestBody VotoDTO votoDTO) {
        return new ResponseEntity<>(votoService.crearVoto(votoDTO), HttpStatus.CREATED);
    }

    //Obtener los datos
    @GetMapping(value = "/{usuarioId}")
    public ResponseEntity<?> obtenerLosVotosDeUnUsuario(@PathVariable("usuarioId") Long usuarioId) {
        return new ResponseEntity<>(votoService.obtenerVotos(usuarioId), HttpStatus.OK);
    }

}