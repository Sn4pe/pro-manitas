package com.promanitas.promanitas.services;

import com.promanitas.promanitas.entities.ProviderEntity;
import com.promanitas.promanitas.entities.ProviderServiceEntity;
import com.promanitas.promanitas.entities.ServiceEntity;
import com.promanitas.promanitas.repos.IServiceRepository;
import com.promanitas.promanitas.services.interfaces.IServicioService;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioService implements IServicioService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServicioService.class);

    @Autowired
    private IServiceRepository serviceRepository;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private ProviderServicioService providerServicioService;

    @Override
    public List<ServiceEntity> getAllServicios() {
        try {
            return serviceRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error while fetching all services: {}", e.getMessage());
            throw new RuntimeException("Error fetching services");
        }
    }

    @Override
    public Optional<ServiceEntity> getServicioById(Long id) {
        try {
            return serviceRepository.findById(id);
        } catch (Exception e) {
            LOGGER.error("Error while fetching service by ID: {}", e.getMessage());
            throw new RuntimeException("Error fetching service by ID");
        }
    }

    @Override
    public ServiceEntity saveServicio(ServiceEntity servicio) {
        return serviceRepository.save(servicio);
    }

    @Override
    public ServiceEntity createService(ServiceEntity serviceEntity, Long providerId) throws ServiceException {
        try {
            ServiceEntity service = this.saveServicio(
                    new ServiceEntity(serviceEntity.getNombreServicio(), serviceEntity.getDescripcion(), serviceEntity.getTarifas())
            );

            Optional<ProviderEntity> providerOptional = providerService.getProviderById(providerId);
            if (providerOptional.isEmpty()) {
                throw new ServiceException("Provider not found");
            }

            ProviderEntity provider = providerOptional.get();
            ProviderServiceEntity providerServiceEntity = new ProviderServiceEntity(null, provider, service);
            providerServicioService.saveProviderService(providerServiceEntity);

            return service;
        } catch (Exception e) {
            LOGGER.error("Error while creating service: {}", e.getMessage());
            throw new ServiceException("Error creating service");
        }
    }
}
