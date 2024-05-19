package com.promanitas.promanitas.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


@Entity
@Table(name = "proveedores")
public class ProviderEntity {

    @Id
    private Long id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    @JsonManagedReference
    private UserEntity user;
    private String descripcion_servicio;
    private Boolean disponibilidad;

    public ProviderEntity(Long id, UserEntity user, String descripcion_servicio, Double tarifas, Boolean disponibilidad) {
        this.id = id;
        this.user = user;
        this.descripcion_servicio = descripcion_servicio;
        this.disponibilidad = disponibilidad;
    }

    public ProviderEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getuser() {
        return user;
    }


    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getDescripcion_servicio() {
        return descripcion_servicio;
    }

    public void setDescripcion_servicio(String descripcion_servicio) {
        this.descripcion_servicio = descripcion_servicio;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
