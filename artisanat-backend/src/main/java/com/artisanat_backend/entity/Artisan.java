package com.artisanat_backend.entity;

import com.artisanat_backend.enums.Role;
import com.artisanat_backend.enums.Specialty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Artisan extends User {

    public Artisan() {
        this.setRole(Role.ARTISAN);
    }

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    private String location;
    private int experience;

    @OneToMany(mappedBy = "artisan")
    private List<Product> products;

    // Getters and Setters
}
