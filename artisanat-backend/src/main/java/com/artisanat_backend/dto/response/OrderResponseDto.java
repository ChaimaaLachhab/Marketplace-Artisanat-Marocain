package com.artisanat_backend.dto.response;

import com.artisanat_backend.enums.Status;
import com.artisanat_backend.model.Customer;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;
    private int quantity;
    private String orderDate;
    private String location;
    private Status status;
    private double totalAmount;
    private Customer customer;
    private List<SubOrderResponseDto> subOrders;
}
