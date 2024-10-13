package com.artisanat_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Cart cart;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
}