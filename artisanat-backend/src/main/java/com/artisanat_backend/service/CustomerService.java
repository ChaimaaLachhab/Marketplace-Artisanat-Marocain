package com.artisanat_backend.service;

import com.artisanat_backend.dto.request.CustomerRequestDto;
import com.artisanat_backend.mapper.UserMapper;
import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.Media;
import com.artisanat_backend.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private UserMapper userMapper;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Transactional
    public Customer updateCustomer(CustomerRequestDto customerDTO, Customer customer, MultipartFile userPhoto) {
        Customer updatedCustomer = userMapper.partialUpdateCustomer(customerDTO, customer);

        if (userPhoto != null && !userPhoto.isEmpty()) {
            Media media=  mediaService.updateMediaForUser(userPhoto, updatedCustomer);
            updatedCustomer.setUserPhoto(media);
            return customerRepository.save(updatedCustomer);
        }

        return customerRepository.save(updatedCustomer);
    }


    public int getLoyaltyPoints(Customer customer) {
        return customer.getLoyalty().getPoints();
    }

    public int getLoyaltyPoints(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
        return customer.getLoyalty().getPoints();
    }
}
