package com.artisanat_backend.service;

import com.artisanat_backend.dto.LoginUserDto;
import com.artisanat_backend.dto.request.AdminRequestDto;
import com.artisanat_backend.dto.request.ArtisanRequestDto;
import com.artisanat_backend.dto.request.CustomerRequestDto;
import com.artisanat_backend.model.Admin;
import com.artisanat_backend.model.Artisan;
import com.artisanat_backend.model.User;
import com.artisanat_backend.mapper.UserMapper;
import com.artisanat_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public User signup(CustomerRequestDto input) {
        if (userRepository.findByUsername(input.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.findByEmail(input.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = userMapper.toCustomerEntity(input);
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        return userRepository.save(user);
    }

    public Artisan addArtisan(ArtisanRequestDto input) {
        if (userRepository.findByUsername(input.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.findByEmail(input.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Artisan artisan = userMapper.toArtisanEntity(input);
        artisan.setPassword(passwordEncoder.encode(input.getPassword()));
        return userRepository.save(artisan);
    }

    public Admin addAdmin(AdminRequestDto input) {
        if (userRepository.findByUsername(input.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.findByEmail(input.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Admin admin = userMapper.toAdminEntity(input);
        admin.setPassword(passwordEncoder.encode(input.getPassword()));
        return userRepository.save(admin);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUserNameOrEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsernameOrEmail(input.getUserNameOrEmail(), input.getUserNameOrEmail());
    }
}
