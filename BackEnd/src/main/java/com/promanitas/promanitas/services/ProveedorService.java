package com.promanitas.promanitas.services;

import com.promanitas.promanitas.entities.ERole;
import com.promanitas.promanitas.entities.ProviderEntity;
import com.promanitas.promanitas.entities.RoleEntity;
import com.promanitas.promanitas.entities.UserEntity;
import com.promanitas.promanitas.repos.IProviderRepository;
import com.promanitas.promanitas.repos.IRoleRepository;
import com.promanitas.promanitas.repos.IUserRepository;
import com.promanitas.promanitas.services.interfaces.IProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService implements IProviderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProveedorService.class);
    @Autowired
    IProviderRepository providerRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IRoleRepository roleRepository;

    @Override
    public List<ProviderEntity> getAllProviders() {
        try {
            return providerRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error while fetching all users: {}", e.getMessage());
            throw new RuntimeException("Error fetching users");
        }
    }

    @Override
    public Optional<ProviderEntity> getProviderById(Long providerId) {
        try {
            return providerRepository.findById(providerId);
        } catch (Exception e) {
            LOGGER.error("Error while fetching user by ID: {}", e.getMessage());
            throw new RuntimeException("Error fetching user by ID");
        }
    }

    @Override
    public ProviderEntity createProviderFromCustomer(Long customerId, String descripcion_servicio, Boolean disponibilidad) {
        UserEntity customer = userRepository.findById(customerId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with customerId: " + customerId));

        // Crea una nueva instancia de 'ProviderEntity'
        ProviderEntity provider = new ProviderEntity();

        // Asigna el cliente al proveedor
        provider.setuser(customer);

        // Establece las propiedades adicionales
        provider.setDescripcion_servicio(descripcion_servicio);
        provider.setDisponibilidad(disponibilidad);

        RoleEntity providerRole = roleRepository.findByName(ERole.ROLE_PROVIDER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        customer.getRoles().add(providerRole);
        userRepository.save(customer);

        // Guarda el proveedor en la base de datos y lo devuelve
        return providerRepository.save(provider);
    }
}
