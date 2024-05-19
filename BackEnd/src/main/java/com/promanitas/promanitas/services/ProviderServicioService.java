package com.promanitas.promanitas.services;

import com.promanitas.promanitas.entities.ProviderServiceEntity;
import com.promanitas.promanitas.entities.ServiceEntity;
import com.promanitas.promanitas.repos.IProviderServiceRepository;
import com.promanitas.promanitas.services.interfaces.IProviderServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProviderServicioService implements IProviderServicioService {

    @Autowired
    private IProviderServiceRepository providerServiceRepository;

    @Override
    public void saveProviderService(ProviderServiceEntity providerServiceEntity) {
        providerServiceRepository.save(providerServiceEntity);
    }

    @Override
    public Optional<ProviderServiceEntity> getByServiceId(Long serviceId) {
        ServiceEntity service = new ServiceEntity();
        service.setId(serviceId);
        return providerServiceRepository.findByServicio(service);
    }
}
