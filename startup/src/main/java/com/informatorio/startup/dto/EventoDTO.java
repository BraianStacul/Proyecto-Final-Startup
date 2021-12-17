package com.informatorio.startup.dto;

/* CLASE Y ATRIBUTOS */

public class EventoDTO {

    private Long eventoId;
    private Long suscriptoId;

    /* GETTERS Y SETTERS */

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }

    public Long getSuscriptoId() {
        return suscriptoId;
    }

    public void setSuscriptoId(Long suscriptoId) {
        this.suscriptoId = suscriptoId;
    }
}