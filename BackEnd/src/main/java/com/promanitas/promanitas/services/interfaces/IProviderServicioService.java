package com.promanitas.promanitas.services.interfaces;

import com.promanitas.promanitas.entities.ProviderServiceEntity;

import java.util.Optional;

public interface IProviderServicioService {


    Optional<ProviderServiceEntity> getByServiceId(Long serviceId);
    void saveProviderService(ProviderServiceEntity servicio);

}
