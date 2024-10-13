package com.artisanat_backend.controller;

import com.artisanat_backend.dto.*;
import com.artisanat_backend.dto.request.AdminRequestDto;
import com.artisanat_backend.dto.request.ArtisanRequestDto;
import com.artisanat_backend.dto.request.CustomerRequestDto;
import com.artisanat_backend.dto.response.AdminResponseDto;
import com.artisanat_backend.dto.response.ArtisanResponseDto;
import com.artisanat_backend.dto.response.CustomerResponseDto;
import com.artisanat_backend.mapper.UserMapper;
import com.artisanat_backend.model.Admin;
import com.artisanat_backend.model.Artisan;
import com.artisanat_backend.model.Customer;
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
    private final UserMapper userMapper;

    @Autowired
    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, UserMapper userMapper) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
    }


    @PostMapping("/signup")
    public ResponseEntity<CustomerResponseDto> register(@RequestBody CustomerRequestDto customerDTO) {
        User registeredUser = authenticationService.signup(customerDTO);
        CustomerResponseDto responseDto = userMapper.toCustomerResponseDto((Customer) registeredUser); // Assuming registeredUser is of type Customer
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/add-artisan")
    public ResponseEntity<ArtisanResponseDto> addArtisan(@RequestBody ArtisanRequestDto artisanDTO) {
        User newArtisan = authenticationService.addArtisan(artisanDTO);
        ArtisanResponseDto responseDto = userMapper.toArtisanResponseDto((Artisan) newArtisan); // Assuming newArtisan is of type Artisan
        return ResponseEntity.ok(responseDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add-admin")
    public ResponseEntity<AdminResponseDto> addAdmin(@RequestBody AdminRequestDto adminDTO) {
        User newAdmin = authenticationService.addAdmin(adminDTO);
        AdminResponseDto responseDto = userMapper.toAdminResponseDto((Admin) newAdmin); // Assuming newAdmin is of type Admin
        return ResponseEntity.ok(responseDto);
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
