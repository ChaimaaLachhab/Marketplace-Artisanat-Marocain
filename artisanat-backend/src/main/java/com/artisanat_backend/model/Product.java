package com.artisanat_backend.model;

import com.artisanat_backend.enums.Category;
import com.artisanat_backend.enums.Collection;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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

    @Transient
    private double rating;

    @Enumerated(EnumType.STRING)
    private Collection collection;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "artisan_id")
    private Artisan artisan;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart carts;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Media> media;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviews;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orders;


    @PostLoad
    @PostPersist
    @PostUpdate
    private void calculateAverageRating() {
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
