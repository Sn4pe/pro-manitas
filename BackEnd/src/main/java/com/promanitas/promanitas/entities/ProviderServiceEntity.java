package com.promanitas.promanitas.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "proveedor_servicio")
public class ProviderServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id")
    private ProviderEntity proveedor;

    @ManyToOne
    @JoinColumn(name = "id_servicio", referencedColumnName = "id_servicio")
    private ServiceEntity servicio;

    public ProviderServiceEntity() {
    }

    public ProviderServiceEntity(Long id, ProviderEntity proveedor, ServiceEntity servicio) {
        this.id = id;
        this.proveedor = proveedor;
        this.servicio = servicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProviderEntity getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProviderEntity proveedor) {
        this.proveedor = proveedor;
    }

    public ServiceEntity getServicio() {
        return servicio;
    }

    public void setServicio(ServiceEntity servicio) {
        this.servicio = servicio;
    }
}
