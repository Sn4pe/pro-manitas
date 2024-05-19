package com.promanitas.promanitas.services.interfaces;

import com.promanitas.promanitas.entities.ServiceEntity;
import org.hibernate.service.spi.ServiceException;

import java.util.List;
import java.util.Optional;

public interface IServicioService {
    List<ServiceEntity> getAllServicios();
    Optional<ServiceEntity> getServicioById(Long id);
    ServiceEntity saveServicio(ServiceEntity servicio);
    ServiceEntity createService(ServiceEntity serviceEntity, Long providerId) throws ServiceException;
}
