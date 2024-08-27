package com.artisanat_backend.configs;

import com.artisanat_backend.entity.Admin;
import com.artisanat_backend.entity.User;
import com.artisanat_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByEmail("admin@gmail.com")) {
            User admin = new Admin();
            admin.setEmail("admin@gmaiil.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setUsername("admin");
            userRepository.save(admin);
        }
    }
}

