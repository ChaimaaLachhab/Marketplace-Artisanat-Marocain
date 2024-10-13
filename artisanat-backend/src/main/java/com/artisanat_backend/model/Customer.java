package com.artisanat_backend.model;

import com.artisanat_backend.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    public Customer() {
        this.setRole(Role.CUSTOMER);
        this.loyalty = new Loyalty();
    }

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Review> reviews;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToOne(cascade = CascadeType.ALL)
    private Loyalty loyalty;

    @JsonIgnore
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Cart cart;

}
