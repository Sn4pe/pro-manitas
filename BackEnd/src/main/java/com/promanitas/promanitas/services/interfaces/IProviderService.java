package com.promanitas.promanitas.services.interfaces;

import com.promanitas.promanitas.entities.ProviderEntity;

import java.util.List;
import java.util.Optional;

public interface IProviderService {
    List<ProviderEntity> getAllProviders();
    Optional<ProviderEntity> getProviderById(Long providerId);
    ProviderEntity createProviderFromCustomer(Long customerId, String descripcion_servicio, Boolean disponibilidad);
    boolean isProvider(Long userId);
}
