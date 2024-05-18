package com.promanitas.promanitas.repos;

import com.promanitas.promanitas.entities.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IServiceRepository extends JpaRepository<ServiceEntity, Long> {


}
