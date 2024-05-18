package com.promanitas.promanitas.repos;

import com.promanitas.promanitas.entities.RefreshTokenEntity;
import com.promanitas.promanitas.entities.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByToken(String token);

    @Transactional
    @Modifying
    int deleteByUser(UserEntity user);
}
