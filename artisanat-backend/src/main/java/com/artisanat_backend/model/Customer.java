package com.artisanat_backend.model;

import com.artisanat_backend.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Customer extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Customer() {
        this.setRole(Role.CUSTOMER);
    }

    @OneToMany(mappedBy = "customer")
    private List<Review> reviews;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @OneToOne(cascade = CascadeType.ALL)
    private Loyalty loyalty;

    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;

}
