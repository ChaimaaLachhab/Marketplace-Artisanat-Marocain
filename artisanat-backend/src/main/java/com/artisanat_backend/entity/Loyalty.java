package com.artisanat_backend.entity;

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

    private int points;

    @OneToOne(mappedBy = "loyalty")
    private Customer customer;

}
