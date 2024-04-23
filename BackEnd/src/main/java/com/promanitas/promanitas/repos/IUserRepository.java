package com.promanitas.promanitas.repos;

import com.promanitas.promanitas.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, Integer> {

}
