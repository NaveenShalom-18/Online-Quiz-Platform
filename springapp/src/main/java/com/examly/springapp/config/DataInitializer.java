package com.examly.springapp.config;

import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByEmail("admin@test.com")) {
            User admin = new User();
            admin.setName("Admin User");
            admin.setEmail("admin@test.com");
            admin.setPasswordHash("admin123");
            admin.setRole(User.Role.STUDENT);
            userRepository.save(admin);
            System.out.println("Created default user: admin@test.com / admin123");
        }
    }
}