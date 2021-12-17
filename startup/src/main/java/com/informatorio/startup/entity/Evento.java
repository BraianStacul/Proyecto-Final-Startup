package com.informatorio.startup.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

/* CLASE Y ATRIBUTOS */
@Entity
@Where(clause = "activo = true")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo Nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El campo Descripcion no puede estar vacio")
    private String detalles;

    @DateTimeFormat
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;

    @DateTimeFormat
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFinalizacion;

    @Column(name = "estado", nullable = false, columnDefinition = "varchar(32) default 'ABIERTO'")
    @Enumerated(value = EnumType.STRING)
    private EventoStatus estado = EventoStatus.ABIERTO;

    @ManyToMany(mappedBy = "eventos")
    @JsonIgnoreProperties({"descripcion","contenido","fechaInicio","objetivo","publicado","tags"})
    @OrderBy("contadorVotos DESC")
    private List<Emprendimiento> emprendimientos;

    private Double premio;

    private Boolean activo = true;

    /* CONSTRUCTORES */

    public Evento(Long id, String nombre, String detalles, LocalDate fechaInicio, LocalDate fechaFinalizacion, EventoStatus estado, List<Emprendimiento> emprendimientos, Double premio, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.detalles = detalles;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
        this.estado = estado;
        this.emprendimientos = emprendimientos;
        this.premio = premio;
        this.activo = activo;
    }

    public Evento() {
    }

    /* GETTERS Y SETTERS */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public EventoStatus getEstado() {
        return estado;
    }

    public void setEstado(EventoStatus estado) {
        this.estado = estado;
    }

    public List<Emprendimiento> getEmprendimientos() {
        return emprendimientos;
    }

    public void setEmprendimientos(List<Emprendimiento> emprendimientos) {
        this.emprendimientos = emprendimientos;
    }

    public Double getPremio() {
        return premio;
    }

    public void setPremio(Double premio) {
        this.premio = premio;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    /* TO STRING */

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", detalles='" + detalles + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFinalizacion=" + fechaFinalizacion +
                ", estado=" + estado +
                ", emprendimientos=" + emprendimientos +
                ", premio=" + premio +
                ", activo=" + activo +
                '}';
    }
}