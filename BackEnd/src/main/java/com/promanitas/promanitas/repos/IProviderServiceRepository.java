package com.promanitas.promanitas.repos;

import com.promanitas.promanitas.entities.ProviderServiceEntity;
import com.promanitas.promanitas.entities.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProviderServiceRepository extends JpaRepository<ProviderServiceEntity, Long> {
    Optional<ProviderServiceEntity> findByServicio(ServiceEntity servicio);
}
