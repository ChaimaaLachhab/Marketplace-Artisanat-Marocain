package com.artisanat_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SubOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sub_order_id")
    private SubOrder subOrder;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
