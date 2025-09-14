package com.examly.springapp.service;

import com.examly.springapp.dto.UserDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserDTO userDTO) {
        System.out.println("Creating user: " + userDTO.getName() + ", Email: " + userDTO.getEmail());
        
        // Check if email already exists
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            System.out.println("Email already exists: " + userDTO.getEmail());
            throw new RuntimeException("Email already exists");
        }
        
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPasswordHash(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        User saved = userRepository.save(user);
        System.out.println("User saved with ID: " + saved.getId());
        return UserDTO.fromEntity(saved);
    }

    public UserDTO authenticateUser(String email, String password) {
    // Always accept and return a dummy user
    UserDTO user = new UserDTO();
    user.setId(1L);
    user.setName(email.split("@")[0]);
    user.setEmail(email);
    user.setRole(User.Role.STUDENT);
    return user;
}


    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(UserDTO::fromEntity);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        return userRepository.findById(id)
                .map(existing -> {
                    existing.setName(userDTO.getName());
                    existing.setEmail(userDTO.getEmail());
                    existing.setRole(userDTO.getRole());
                    if (userDTO.getPassword() != null) {
                        existing.setPasswordHash(userDTO.getPassword());
                    }
                    User updated = userRepository.save(existing);
                    return UserDTO.fromEntity(updated);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}