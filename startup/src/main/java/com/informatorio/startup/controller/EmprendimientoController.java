package com.informatorio.startup.controller;

import com.informatorio.startup.repository.EmprendimientoRepository;
import com.informatorio.startup.repository.EventoRepository;
import com.informatorio.startup.dto.EventoDTO;
import com.informatorio.startup.entity.Emprendimiento;
import com.informatorio.startup.repository.EmprendimientoRepository;
import com.informatorio.startup.repository.EventoRepository;
import com.informatorio.startup.service.EmprendimientoService;
import com.informatorio.startup.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/* Inicio del Controlador */

@RestController
public class EmprendimientoController {
    private final EmprendimientoRepository emprendimientoRepository;
    private final EmprendimientoService emprendimientoService;
    private final EventoRepository eventoRepository;
    private final EventoService eventoService;

    // Se aplicacn las Dependencias

    @Autowired
    public EmprendimientoController(EmprendimientoRepository emprendimientoRepository, EmprendimientoService emprendimientoService, EventoRepository eventoRepository, EventoService eventoService) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.emprendimientoService = emprendimientoService;
        this.eventoRepository = eventoRepository;
        this.eventoService = eventoService;
    }

    /* Acciones del Controlador */
    //Crear el Emprendimiento
    @PostMapping(value = "/usuarios/{id}/emprendimientos")
    public ResponseEntity<?> crearEmprendimiento(@PathVariable("id") Long usuarioId,
                                                 @Valid @RequestBody Emprendimiento emprendimiento) {
        return new ResponseEntity<>(emprendimientoService.crear(emprendimiento, usuarioId), HttpStatus.CREATED);
    }

    //Eliminar el Emprendimiento
    @DeleteMapping(value = "/emprendimientos/{id}/eliminar")
    public Emprendimiento eliminarEmprendimiento(@PathVariable("id") Long id, Emprendimiento emprendimiento) {
        return this.emprendimientoService.eliminar(emprendimiento, id);
    }

    //Modificar el Emprendimiento
    @PutMapping(value = "/emprendimientos/{id}")
    public Emprendimiento modificarEmprendimiento(@PathVariable("id") Long id,
                                                  @Valid @RequestBody Emprendimiento emprendimiento) {
        return this.emprendimientoService.modificar(emprendimiento, id);
    }

    //Obtener todos los Emprendimientos
    @GetMapping(value = "/emprendimientos")
    public ResponseEntity<?> obtenerTodosLosEmprendimientos(@RequestParam(name = "nombre", required = false) String nombre) {
        return new ResponseEntity<>(emprendimientoService.obtenerTodos(nombre) ,HttpStatus.OK);
    }

    @GetMapping(value = "/emprendimientos/{tag}/tags")
    public ResponseEntity<?> obtenerPorTag(@PathVariable("tag") String tag){
        return ResponseEntity.ok(emprendimientoService.obtenerPorTag(tag));
    }

    //Obtener los Emprendimientos no Publicados
    @GetMapping(value = "/emprendimientos/no_publicados")
    public ResponseEntity<?> obtenerEmprendimientosNoPublicados() {
        return new ResponseEntity<>(emprendimientoService.obtenerNoPublicados(), HttpStatus.OK);
    }

    //Registrar Evento al Emprendimiento
    @PostMapping(value = "/emprendimientos/{emprendimientoId}/eventos/{eventoId}")
    public ResponseEntity<?> registrarEvento(@PathVariable("emprendimientoId") Long emprendimientoId, @PathVariable("eventoId") Long eventoId, EventoDTO eventoDTO) {
        emprendimientoRepository.findById(emprendimientoId);
        eventoRepository.findById(eventoId);
        return new ResponseEntity<>(eventoService.registrar(emprendimientoId, eventoId, eventoDTO), HttpStatus.CREATED);
    }

}