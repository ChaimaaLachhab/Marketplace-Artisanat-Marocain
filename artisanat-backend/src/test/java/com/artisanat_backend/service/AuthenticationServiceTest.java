package com.artisanat_backend.service;

import com.artisanat_backend.dto.AdminDTO;
import com.artisanat_backend.dto.ArtisanDTO;
import com.artisanat_backend.dto.CustomerDTO;
import com.artisanat_backend.dto.LoginUserDto;
import com.artisanat_backend.model.Admin;
import com.artisanat_backend.model.Artisan;
import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.User;
import com.artisanat_backend.mapper.UserMapper;
import com.artisanat_backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void signup() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setUsername("testuser");
        customerDTO.setEmail("test@example.com");
        customerDTO.setPassword("password");

        Customer customer = new Customer();
        when(userRepository.findByUsername(customerDTO.getUsername())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(customerDTO.getEmail())).thenReturn(Optional.empty());
        when(userMapper.toCustomerEntity(customerDTO)).thenReturn(customer);
        when(passwordEncoder.encode(customerDTO.getPassword())).thenReturn("encoded_password");
        when(userRepository.save(customer)).thenReturn(customer);

        User result = authenticationService.signup(customerDTO);

        assertNotNull(result);
        verify(userRepository, times(1)).findByUsername(customerDTO.getUsername());
        verify(userRepository, times(1)).findByEmail(customerDTO.getEmail());
        verify(passwordEncoder, times(1)).encode(customerDTO.getPassword());
        verify(userRepository, times(1)).save(customer);
    }

    @Test
    void addArtisan() {
        ArtisanDTO artisanDTO = new ArtisanDTO();
        artisanDTO.setUsername("artisanUser");
        artisanDTO.setEmail("artisan@example.com");
        artisanDTO.setPassword("password");

        Artisan artisan = new Artisan();
        when(userRepository.findByUsername(artisanDTO.getUsername())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(artisanDTO.getEmail())).thenReturn(Optional.empty());
        when(userMapper.toArtisanEntity(artisanDTO)).thenReturn(artisan);
        when(passwordEncoder.encode(artisanDTO.getPassword())).thenReturn("encoded_password");
        when(userRepository.save(artisan)).thenReturn(artisan);

        Artisan result = authenticationService.addArtisan(artisanDTO);

        assertNotNull(result);
        verify(userRepository, times(1)).findByUsername(artisanDTO.getUsername());
        verify(userRepository, times(1)).findByEmail(artisanDTO.getEmail());
        verify(passwordEncoder, times(1)).encode(artisanDTO.getPassword());
        verify(userRepository, times(1)).save(artisan);
    }

    @Test
    void addAdmin() {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setUsername("adminUser");
        adminDTO.setEmail("admin@example.com");
        adminDTO.setPassword("password");

        Admin admin = new Admin();
        when(userRepository.findByUsername(adminDTO.getUsername())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(adminDTO.getEmail())).thenReturn(Optional.empty());
        when(userMapper.toAdminEntity(adminDTO)).thenReturn(admin);
        when(passwordEncoder.encode(adminDTO.getPassword())).thenReturn("encoded_password");
        when(userRepository.save(admin)).thenReturn(admin);

        Admin result = authenticationService.addAdmin(adminDTO);

        assertNotNull(result);
        verify(userRepository, times(1)).findByUsername(adminDTO.getUsername());
        verify(userRepository, times(1)).findByEmail(adminDTO.getEmail());
        verify(passwordEncoder, times(1)).encode(adminDTO.getPassword());
        verify(userRepository, times(1)).save(admin);
    }

    @Test
    void authenticate() {
        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setUserNameOrEmail("testuser");
        loginUserDto.setPassword("password");

        User user = mock(User.class);
        when(userRepository.findByUsernameOrEmail(loginUserDto.getUserNameOrEmail(), loginUserDto.getUserNameOrEmail()))
                .thenReturn(user);

        User result = authenticationService.authenticate(loginUserDto);

        assertNotNull(result);
        verify(authenticationManager, times(1)).authenticate(
                new UsernamePasswordAuthenticationToken(loginUserDto.getUserNameOrEmail(), loginUserDto.getPassword())
        );
        verify(userRepository, times(1)).findByUsernameOrEmail(loginUserDto.getUserNameOrEmail(), loginUserDto.getUserNameOrEmail());
    }

}
