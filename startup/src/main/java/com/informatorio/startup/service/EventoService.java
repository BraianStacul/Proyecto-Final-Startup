package com.informatorio.startup.service;

import com.informatorio.startup.dto.EventoDTO;
import com.informatorio.startup.entity.*;
import com.informatorio.startup.repository.EmprendimientoRepository;
import com.informatorio.startup.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/*
------------
INICIO DEL SERVICIO
------------
 */
@Service
@Configuration
@EnableScheduling
public class EventoService {
    private final EmprendimientoRepository emprendimientoRepository;
    private final EventoRepository eventoRepository;

    //SE APLICAN LAS DEPENDENCIAS
    @Autowired
    public EventoService(EmprendimientoRepository emprendimientoRepository, EventoRepository eventoRepository){
        this.emprendimientoRepository = emprendimientoRepository;
        this.eventoRepository = eventoRepository;
    }

    /*
    ------------
    ACCIONES DEL SERVICIO
    ------------
    */

    //CREAR EVENTO
    public Evento crear(Evento evento){
        return eventoRepository.save(evento);
    }

    //ELIMINAR EVENTO
    public Evento eliminar(Evento evento, Long id){
        Evento eventoEliminado = eventoRepository.getById(id);
        eventoEliminado.setActivo(false);
        return eventoRepository.save(eventoEliminado);
    }

    //ACTUALIZAR EL ESTADO DE UN EVENTO
    public void actualizarStatus(Evento evento, LocalDate ahora){
        if (evento.getFechaInicio().isBefore(ahora)){
            evento.setEstado(EventoStatus.EN_CURSO);
        }
        if (evento.getFechaFinalizacion().isBefore(ahora)){
            evento.setEstado(EventoStatus.FINALIZADO);
        }
    }

    //ACTUALIZAR LA FECHA DE UN EVENTO
    public void actualizarFecha(){
        List<Evento> eventos = eventoRepository.findAll();
        LocalDate ahora = LocalDate.now();
        eventos.stream().forEach(evento -> actualizarStatus(evento, ahora));
        eventoRepository.saveAll(eventos);
        System.out.println("Se actualizó el evento");
    }

    //REGISTRAR EMPRENDIMIENTO A EVENTO
    public Emprendimiento registrar(Long emprendimientoId, Long eventoId, EventoDTO eventoDTO){
        Emprendimiento emprendimientoRegistrado = emprendimientoRepository.getById(emprendimientoId);
        Evento eventoRegistrado = eventoRepository.getById(eventoId);
        if (eventoRegistrado.getEstado() == EventoStatus.ABIERTO) { emprendimientoRegistrado.agregarEvento(eventoRegistrado);
            System.out.println("La Suscripcion se realizo correctamente");
        } else if (eventoRegistrado.getEstado() == EventoStatus.EN_CURSO) {
            System.out.println("El evento ya se encuentra en curso, no es posible suscribirse");
        } else { System.out.println("El evento ha finalizado."); }
        return emprendimientoRepository.save(emprendimientoRegistrado);
    }

    //OBTENER RANK DE EVENTOS
    public Optional<Evento> rankear(Long id) {
        return eventoRepository.findById(id);
    }

    //OBTENER TODOS LOS EVENTOS
    public List<Evento> obtenerTodos(){
        return eventoRepository.findAll();
    }


    //BUSCAR EVENTOS POR ID
    public Optional<Evento> buscarEventoId(Long id){
        return eventoRepository.findById(id);
    }

    //MODIFICAR PARAMETROS DEL EVENTO
    public Evento modificar(Evento evento, Long id) {
        Evento eventoNuevo = eventoRepository.getById(id);

        if (!evento.getNombre().trim().isEmpty()) {
            eventoNuevo.setNombre(evento.getNombre()); }

        if (!evento.getDetalles().trim().isEmpty()) {
            eventoNuevo.setDetalles(evento.getDetalles()); }

        if (evento.getPremio() != null && evento.getPremio() > 0) {
            eventoNuevo.setPremio(evento.getPremio()); }

        if (!evento.getActivo()) {
            eventoNuevo.setActivo(false); }

        if (evento.getEstado() != null) {
            eventoNuevo.setEstado(evento.getEstado());
        }

        return eventoRepository.save(eventoNuevo);
    }
}
