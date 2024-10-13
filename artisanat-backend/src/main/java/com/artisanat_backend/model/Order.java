package com.artisanat_backend.model;

import com.artisanat_backend.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private LocalDateTime orderDate;
    private String location;

    @Enumerated(EnumType.STRING)
    private Status status;

    private double totalAmount;

    @JsonIgnore
    @ManyToOne
    private Customer customer;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<SubOrder> subOrders;
}
