package com.artisanat_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
