package com.promanitas.promanitas.services.interfaces;

import com.promanitas.promanitas.entities.UserEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserEntity> getAllUsers();
    UserEntity createUser(UserEntity user);
    Optional<UserEntity> getUserById(Long userId);
    UserEntity updateUser(Long userId, UserEntity newUser);
    HashMap<String, String> deleteUser(Long userId);
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
