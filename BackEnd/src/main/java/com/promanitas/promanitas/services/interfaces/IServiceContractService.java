package com.promanitas.promanitas.services.interfaces;

import com.promanitas.promanitas.entities.ServiceContractEntity;

import java.util.List;

public interface IServiceContractService {
    ServiceContractEntity contratarServicio(Long clienteId, Long servicioId);
    List<ServiceContractEntity> getServicesByClientId(Long clientId);
}
