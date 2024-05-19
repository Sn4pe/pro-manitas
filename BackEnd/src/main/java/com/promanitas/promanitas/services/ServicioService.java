package com.promanitas.promanitas.services;

import com.promanitas.promanitas.entities.ProviderEntity;
import com.promanitas.promanitas.entities.ProviderServiceEntity;
import com.promanitas.promanitas.entities.ServiceEntity;
import com.promanitas.promanitas.repos.IServiceRepository;
import com.promanitas.promanitas.services.interfaces.IProviderService;
import com.promanitas.promanitas.services.interfaces.IProviderServicioService;
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
    private IProviderService providerService;

    @Autowired
    private IProviderServicioService providerServicioService;

    @Override
    public List<ServiceEntity> getAllServicios() {
        return serviceRepository.findAll();
    }

    @Override
    public Optional<ServiceEntity> getServicioById(Long id) {
        return serviceRepository.findById(id);
    }

    @Override
    public ServiceEntity saveServicio(ServiceEntity servicio) {
        return serviceRepository.save(servicio);
    }

    @Override
    public ServiceEntity updateServicio(Long id, ServiceEntity servicio) {
        return serviceRepository.findById(id).map(existingService -> {
            existingService.setNombreServicio(servicio.getNombreServicio());
            existingService.setDescripcion(servicio.getDescripcion());
            existingService.setTarifas(servicio.getTarifas());
            return serviceRepository.save(existingService);
        }).orElseThrow(() -> new RuntimeException("Service not found with id " + id));
    }

    @Override
    public void deleteServicio(Long id) {
        if (serviceRepository.existsById(id)) {
            serviceRepository.deleteById(id);
        } else {
            throw new RuntimeException("Service not found with id " + id);
        }
    }

    @Override
    public ServiceEntity createService(ServiceEntity serviceEntity, Long providerId) throws ServiceException {
        try {
            // Crear y guardar el servicio
            ServiceEntity service = this.saveServicio(
                    new ServiceEntity(serviceEntity.getNombreServicio(), serviceEntity.getDescripcion(), serviceEntity.getTarifas())
            );

            // Buscar el proveedor por ID
            Optional<ProviderEntity> providerOptional = providerService.getProviderById(providerId);
            if (providerOptional.isEmpty()) {
                throw new ServiceException("Provider not found");
            }

            ProviderEntity provider = providerOptional.get();

            // Crear y guardar la relación entre el proveedor y el servicio
            ProviderServiceEntity providerServiceEntity = new ProviderServiceEntity(null, provider, service);
            providerServicioService.saveProviderService(providerServiceEntity);

            return service;
        } catch (Exception e) {
            LOGGER.error("Error while creating service: {}", e.getMessage());
            throw new ServiceException("Error creating service");
        }
    }

    @Override
    public boolean isOwner(Long serviceId, Long userId) {
        // Buscar la relación entre proveedor y servicio
        Optional<ProviderServiceEntity> providerServiceEntityOptional = providerServicioService.getByServiceId(serviceId);

        if (providerServiceEntityOptional.isPresent()) {
            ProviderServiceEntity providerServiceEntity = providerServiceEntityOptional.get();
            // Verificar si el ID del proveedor coincide con el ID del usuario
            return providerServiceEntity.getProveedor().getId().equals(userId);
        }

        return false;
    }
}
