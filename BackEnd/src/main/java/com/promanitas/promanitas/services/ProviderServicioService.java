package com.promanitas.promanitas.services;

import com.promanitas.promanitas.entities.ProviderServiceEntity;
import com.promanitas.promanitas.repos.IProviderServiceRepository;
import com.promanitas.promanitas.services.interfaces.IProviderServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServicioService implements IProviderServicioService {

    @Autowired
    IProviderServiceRepository providerServiceRepository;

    @Override
    public List<ProviderServiceEntity> getAllServicios() {
        try {
            return providerServiceRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching users");
        }
    }

    @Override
    public ProviderServiceEntity saveProviderService(ProviderServiceEntity servicio) {
        return providerServiceRepository.save(servicio);
    }
}
