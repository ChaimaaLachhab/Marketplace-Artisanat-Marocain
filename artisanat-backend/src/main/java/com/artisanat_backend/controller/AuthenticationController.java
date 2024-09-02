package com.artisanat_backend.controller;

import com.artisanat_backend.dto.AdminDTO;
import com.artisanat_backend.dto.ArtisanDTO;
import com.artisanat_backend.dto.CustomerDTO;
import com.artisanat_backend.dto.LoginUserDto;
import com.artisanat_backend.model.LoginResponse;
import com.artisanat_backend.model.User;
import com.artisanat_backend.enums.Role;
import com.artisanat_backend.exception.UserNotFoundException;
import com.artisanat_backend.service.AuthenticationService;
import com.artisanat_backend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody CustomerDTO customerDTO) {
        User registeredUser = authenticationService.signup(customerDTO);
        return ResponseEntity.ok(registeredUser);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add-artisan")
    public ResponseEntity<User> addArtisan(@RequestBody ArtisanDTO artisanDTO) {
        User newTechnician = authenticationService.addArtisan(artisanDTO);
        return ResponseEntity.ok(newTechnician);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add-admin")
    public ResponseEntity<User> addAdmin(@RequestBody AdminDTO adminDTO) {
        User newAdmin = authenticationService.addAdmin(adminDTO);
        return ResponseEntity.ok(newAdmin);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginUserDto loginUserDto) {
        try {
            User authenticatedUser = authenticationService.authenticate(loginUserDto);
            Role role = authenticatedUser.getRole();

            String jwtToken = jwtService.generateToken(authenticatedUser, role);

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(jwtToken);
            loginResponse.setExpiresIn(jwtService.getExpirationTime());

            return ResponseEntity.ok(loginResponse);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login.");
        }
    }
}
