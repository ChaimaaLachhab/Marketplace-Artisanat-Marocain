package com.artisanat_backend.model;

import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;

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

    @OneToMany(mappedBy = "carts", cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToOne(mappedBy = "cart")
    private Customer customer;
}
