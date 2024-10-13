package com.artisanat_backend.model;

import com.artisanat_backend.enums.Role;
import com.artisanat_backend.enums.Specialty;
import com.artisanat_backend.enums.VerificationStatus;
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
public class Artisan extends User {

    public Artisan() {
        this.setRole(Role.ARTISAN);
    }

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    private String location;
    private int experience;

    @Enumerated(EnumType.STRING)
    private VerificationStatus verificationStatus = VerificationStatus.PENDING;

    @JsonIgnore
    @OneToMany(mappedBy = "artisan", fetch = FetchType.EAGER)
    private List<Product> products;

    @JsonIgnore
    @OneToMany(mappedBy = "artisan")
    private List<SubOrder> subOrders;

}
