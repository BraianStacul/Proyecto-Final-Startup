package com.informatorio.startup.controller;

import com.informatorio.startup.entity.Evento;
import com.informatorio.startup.repository.EventoRepository;
import com.informatorio.startup.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/* Inicio del Controlador */
@RestController
@RequestMapping(value = "/eventos")
public class EventoController {
    private final EventoService eventoService;
    private final EventoRepository eventoRepository;

    //Se aplican las dependencias
    @Autowired
    public EventoController(EventoService eventoService, EventoRepository eventoRepository) {
        this.eventoService = eventoService;
        this.eventoRepository = eventoRepository;
    }
    /* Acciones del Controlador */

    //Crear un evento
    @PostMapping
    public ResponseEntity<?> crearEvento(@Valid @RequestBody Evento evento) {
        return new ResponseEntity<>(eventoService.crear(evento), HttpStatus.CREATED);
    }

    //Eliminar un Evento
    @DeleteMapping(value = "/{id}/eliminar")
    public Evento eliminarEvento(@PathVariable("id") Long id, Evento evento) {
        return this.eventoService.eliminar(evento, id);
    }

    //Actualizar el Estado del Evento
    @PutMapping(value = "/{id}/actualizar-estado")
    public void actualizarEvento() {
        this.eventoService.actualizarFecha();
    }

    //Ranking del Evento
    @GetMapping(value = "/{id}/ranking")
    public ResponseEntity<?> rankingDelEvento(@PathVariable("id") Long id) {
        return new ResponseEntity<>(eventoService.rankear(id), HttpStatus.OK);
    }

    //Obtener todos los Eventos
    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        return new ResponseEntity<>(eventoService.obtenerTodos() ,HttpStatus.OK);
    }

    //Modificar un Evento
    @PutMapping(value = "/{id}")
    public Evento modificar(@PathVariable("id") Long id, @Valid @RequestBody Evento evento) {
        return this.eventoService.modificar(evento, id);
    }
}