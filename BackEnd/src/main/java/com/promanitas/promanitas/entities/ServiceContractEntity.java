package com.promanitas.promanitas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "contratos_servicio")
public class ServiceContractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private UserEntity cliente;

    @ManyToOne
    @JoinColumn(name = "id_servicio", referencedColumnName = "id_servicio")
    private ServiceEntity servicio;

    @NotNull
    private Date fechaContrato;

    // Constructor, getters y setters

    public ServiceContractEntity() {}

    public ServiceContractEntity(UserEntity cliente, ServiceEntity servicio, Date fechaContrato) {
        this.cliente = cliente;
        this.servicio = servicio;
        this.fechaContrato = fechaContrato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getCliente() {
        return cliente;
    }

    public void setCliente(UserEntity cliente) {
        this.cliente = cliente;
    }

    public ServiceEntity getServicio() {
        return servicio;
    }

    public void setServicio(ServiceEntity servicio) {
        this.servicio = servicio;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }
}
