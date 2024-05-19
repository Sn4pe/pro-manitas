package com.promanitas.promanitas.repos;

import com.promanitas.promanitas.entities.ServiceContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IServiceContractRepository extends JpaRepository<ServiceContractEntity, Long> {
    List<ServiceContractEntity> findByClienteId(Long clienteId);
}
