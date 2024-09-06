package com.artisanat_backend.dto;

import com.artisanat_backend.enums.Status;
import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.Order;

/**
 * DTO for {@link Order}
 */
public class OrderDto {
    int quantity;
    String orderDate;
    String location;
    Status status;
    double totalAmount;
    Customer customer;
}