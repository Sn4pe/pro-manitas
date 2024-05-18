package com.promanitas.promanitas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "servicios")
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Long id;

    @Column(name = "nombre_servicio")
    @NotBlank(message = "El nombre del servicio no puede estar vacío")
    private String nombreServicio;

    @NotBlank(message = "La descripción del servicio no puede estar vacía")
    private String descripcion;

    @NotNull(message = "La tarifa del servicio no puede estar vacía")
    private Double tarifas;

    public ServiceEntity() {
    }

    public ServiceEntity(Long id, String nombreServicio, String descripcion, Double tarifas) {
        this.id = id;
        this.nombreServicio = nombreServicio;
        this.descripcion = descripcion;
        this.tarifas = tarifas;
    }

    public ServiceEntity(String nombreServicio, String descripcion, Double tarifas) {
        this.nombreServicio = nombreServicio;
        this.descripcion = descripcion;
        this.tarifas = tarifas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public Double getTarifas() {
        return tarifas;
    }

    public void setTarifas(Double tarifas) {
        this.tarifas = tarifas;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
