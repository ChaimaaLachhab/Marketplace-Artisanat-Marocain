package com.artisanat_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating;
    private String comment;
    private LocalDateTime reviewDate = LocalDateTime.now();

    @JsonIgnore
    @ManyToOne
    private Customer customer;

    @JsonIgnore
    @ManyToOne
    private Product product;
}
