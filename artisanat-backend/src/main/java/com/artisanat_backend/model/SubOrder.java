package com.artisanat_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SubOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double subTotal;

    @JsonIgnore
    @ManyToOne
    private Artisan artisan;

    @JsonIgnore
    @ManyToOne
    private Order order;

    @JsonIgnore
    @OneToMany(mappedBy = "subOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubOrderItem> subOrderItems;
}
