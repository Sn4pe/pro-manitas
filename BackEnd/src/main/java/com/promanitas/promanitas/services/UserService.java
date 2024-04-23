package com.promanitas.promanitas.services;

import com.promanitas.promanitas.entities.UserEntity;
import com.promanitas.promanitas.repos.IUserRepository;
import com.promanitas.promanitas.services.interfaces.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    IUserRepository userRepository;

    @Override
    public List<UserEntity> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error while fetching all users: {}", e.getMessage());
            throw new RuntimeException("Error fetching users");
        }
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            LOGGER.error("Error while creating user: {}", e.getMessage());
            throw new RuntimeException("Error creating user");
        }
    }

    @Override
    public Optional<UserEntity> getUserById(Integer userId) {
        try {
            return userRepository.findById(userId);
        } catch (Exception e) {
            LOGGER.error("Error while fetching user by ID: {}", e.getMessage());
            throw new RuntimeException("Error fetching user by ID");
        }
    }

    @Override
    public UserEntity updateUser(Integer userId, UserEntity newUser) {
        try {
            UserEntity existingUser = userRepository.findById(userId).orElse(null);
            if (existingUser != null) {
                existingUser.setNombre(newUser.getNombre());
                existingUser.setApellidos(newUser.getApellidos());
                existingUser.setEmail(newUser.getEmail());
                existingUser.setTelefono(newUser.getTelefono());


                return userRepository.save(existingUser);
            }
            throw new RuntimeException("User not found");
        } catch (Exception e) {
            LOGGER.error("Error while updating user: {}", e.getMessage());
            throw new RuntimeException("Error updating user");
        }
    }

    @Override
    public HashMap<String, String> deleteUser(Integer userId) {
        try {
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "User deleted succesfully!");
            userRepository.deleteById(userId);
            return response;
        } catch (Exception e) {
            LOGGER.error("Error while deleting user: {}", e.getMessage());
            throw new RuntimeException("Error deleting user");
        }
    }
}
