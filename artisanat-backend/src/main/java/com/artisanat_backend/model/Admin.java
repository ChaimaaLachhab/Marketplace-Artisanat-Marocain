package com.artisanat_backend.model;

import com.artisanat_backend.enums.Role;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Entity
public class Admin extends User {

    public Admin() {
        this.setRole(Role.ADMIN);
    }

}
