package com.promanitas.promanitas.services.interfaces;

import com.promanitas.promanitas.entities.ServiceEntity;

import java.util.List;
import java.util.Optional;

public interface IServicioService {

    List<ServiceEntity> getAllServicios();

    Optional<ServiceEntity> getServicioById(Long id);

    ServiceEntity saveServicio(ServiceEntity servicio);

}
