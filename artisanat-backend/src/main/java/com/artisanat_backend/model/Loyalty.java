package com.artisanat_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Loyalty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int points = 0;

    @JsonIgnore
    @OneToOne(mappedBy = "loyalty")
    private Customer customer;
}
