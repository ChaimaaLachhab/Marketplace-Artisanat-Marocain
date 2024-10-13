package com.artisanat_backend.model;

import com.artisanat_backend.enums.Category;
import com.artisanat_backend.enums.Collection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private float price;
    private int stock;
    private LocalDateTime createdAt = LocalDateTime.now();

    private double rating;

    @Enumerated(EnumType.STRING)
    private Collection collection;

    @Enumerated(EnumType.STRING)
    private Category category;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "artisan_id")
    private Artisan artisan;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Media> media;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviews;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<SubOrderItem> subOrderItems;

    @PrePersist
    @PreUpdate
    public void calculateAndPersistRating() {
        if (reviews != null && !reviews.isEmpty()) {
            OptionalDouble average = reviews.stream()
                    .mapToInt(Review::getRating)
                    .average();
            this.rating = average.orElse(0.0);
        } else {
            this.rating = 0.0;
        }
    }
}
