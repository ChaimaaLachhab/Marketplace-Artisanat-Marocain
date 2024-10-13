package com.artisanat_backend.controller;

import com.artisanat_backend.dto.request.CustomerRequestDto;
import com.artisanat_backend.dto.response.CustomerResponseDto;
import com.artisanat_backend.mapper.UserMapper;
import com.artisanat_backend.model.Customer;
import com.artisanat_backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for managing customers.
 */
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final UserMapper userMapper;

    @Autowired
    public CustomerController(CustomerService customerService, UserMapper userMapper) {
        this.customerService = customerService;
        this.userMapper = userMapper;
    }

    /**
     * Retrieve all customers.
     *
     * @return List of all customers.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerResponseDto> customerResponseDtos = customers.stream()
                .map(userMapper::toCustomerResponseDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(customerResponseDtos, HttpStatus.OK);
    }

    /**
     * Retrieve a customer by ID.
     *
     * @return The customer with the specified ID.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/details")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@AuthenticationPrincipal Customer currentCustomer) {
        Customer customer = customerService.getCustomerById(currentCustomer.getId());
        return customer != null
                ? new ResponseEntity<>(userMapper.toCustomerResponseDto(customer), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Update an existing customer.
     *
     * @param customerDTO The customer with updated information.
     * @param userPhoto   The photo to be updated, if any.
     * @param customer    The authenticated customer.
     * @return The updated customer.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @PutMapping(value = "/update", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomerResponseDto> updateCustomer(
            @RequestPart("customer") CustomerRequestDto customerDTO,
            @RequestPart(value = "userPhoto", required = false) MultipartFile userPhoto,
            @AuthenticationPrincipal Customer customer) {

        Customer updatedCustomer = customerService.updateCustomer(customerDTO, customer, userPhoto);
        return new ResponseEntity<>(userMapper.toCustomerResponseDto(updatedCustomer), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{customerId}/loyalty-points")
    public ResponseEntity<Integer> getLoyaltyPoints(@PathVariable Long customerId) {
        int points = customerService.getLoyaltyPoints(customerId);
        return ResponseEntity.ok(points);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/loyalty-points")
    public ResponseEntity<Integer> getLoyaltyPoints(@AuthenticationPrincipal Customer customer) {
        int points = customerService.getLoyaltyPoints(customer);
        return ResponseEntity.ok(points);
    }
}
