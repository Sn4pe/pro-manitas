package com.promanitas.promanitas.repos;

import com.promanitas.promanitas.entities.ProviderServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProviderServiceRepository extends JpaRepository<ProviderServiceEntity, Long> {
}
