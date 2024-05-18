package com.promanitas.promanitas.repos;

import com.promanitas.promanitas.entities.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IProviderRepository extends JpaRepository<ProviderEntity, Long> {

}
