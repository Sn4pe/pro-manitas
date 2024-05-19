package com.promanitas.promanitas.services;

import com.promanitas.promanitas.entities.UserEntity;
import com.promanitas.promanitas.repos.IUserRepository;
import com.promanitas.promanitas.security.services.UserDetailsImpl;
import com.promanitas.promanitas.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService, IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<UserEntity> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public UserEntity updateUser(Long userId, UserEntity newUser) {
        return userRepository.findById(userId).map(user -> {
            user.setUsername(newUser.getUsername());
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            return userRepository.save(user);
        }).orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + userId));
    }

    @Override
    public HashMap<String, String> deleteUser(Long userId) {
        HashMap<String, String> response = new HashMap<>();
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            response.put("message", "User deleted successfully");
        } else {
            response.put("message", "User not found");
        }
        return response;
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

}
