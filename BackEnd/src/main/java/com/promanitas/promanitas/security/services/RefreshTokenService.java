package com.promanitas.promanitas.security.services;

import com.promanitas.promanitas.entities.RefreshTokenEntity;
import com.promanitas.promanitas.entities.UserEntity;
import com.promanitas.promanitas.repos.IRefreshTokenRepository;
import com.promanitas.promanitas.repos.IUserRepository;
import com.promanitas.promanitas.security.jwt.exception.TokenRefreshException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("${promanitas.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private IRefreshTokenRepository IRefreshTokenRepository;

    @Autowired
    private IUserRepository userRepository;

    public Optional<RefreshTokenEntity> findByToken(String token) {
        return IRefreshTokenRepository.findByToken(token);
    }

    public RefreshTokenEntity createRefreshToken(Long userId) {
        this.deleteByUserId(userId);

        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();

        refreshTokenEntity.setUser(userRepository.findById(userId).orElse(null));
        refreshTokenEntity.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshTokenEntity.setToken(UUID.randomUUID().toString());

        refreshTokenEntity = IRefreshTokenRepository.save(refreshTokenEntity);
        return refreshTokenEntity;
    }

    public RefreshTokenEntity verifyExpiration(RefreshTokenEntity token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            IRefreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    public void deleteByUserId(Long userId) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            IRefreshTokenRepository.deleteByUser(user);
        }
    }
}
