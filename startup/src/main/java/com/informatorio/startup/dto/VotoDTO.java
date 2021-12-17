package com.informatorio.startup.dto;

import com.informatorio.startup.entity.VotoGenerate;

import java.time.LocalDateTime;

/* CLASE Y ATRIBUTOS */

public class VotoDTO {

    private VotoGenerate generado;
    private Long usuarioId;
    private Long emprendimientoId;
    private LocalDateTime fechaEmision;
    private Boolean emitido;

    /* GETTERS Y SETTERS */

    public VotoGenerate getGenerado() {
        return generado;
    }

    public void setGenerado(VotoGenerate generado) {
        this.generado = generado;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getEmprendimientoId() {
        return emprendimientoId;
    }

    public void setEmprendimientoId(Long emprendimientoId) {
        this.emprendimientoId = emprendimientoId;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Boolean getEmitido() {
        return emitido;
    }

    public void setEmitido(Boolean emitido) {
        this.emitido = emitido;
    }
}