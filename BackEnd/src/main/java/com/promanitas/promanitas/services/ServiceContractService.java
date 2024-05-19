package com.promanitas.promanitas.services;

import com.promanitas.promanitas.entities.ServiceContractEntity;
import com.promanitas.promanitas.entities.ServiceEntity;
import com.promanitas.promanitas.entities.UserEntity;
import com.promanitas.promanitas.repos.IServiceContractRepository;
import com.promanitas.promanitas.repos.IServiceRepository;
import com.promanitas.promanitas.repos.IUserRepository;
import com.promanitas.promanitas.services.interfaces.IServiceContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ServiceContractService implements IServiceContractService {

    @Autowired
    private IServiceContractRepository serviceContractRepository;

    @Autowired
    private IServiceRepository serviceRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public ServiceContractEntity contratarServicio(Long clienteId, Long servicioId) {
        UserEntity cliente = userRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        ServiceEntity servicio = serviceRepository.findById(servicioId)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        ServiceContractEntity contrato = new ServiceContractEntity(cliente, servicio, new Date());

        return serviceContractRepository.save(contrato);
    }

    @Override
    public List<ServiceContractEntity> getServicesByClientId(Long clientId) {
        return serviceContractRepository.findByClienteId(clientId);
    }
}
