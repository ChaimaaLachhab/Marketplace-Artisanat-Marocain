package com.artisanat_backend.entity;

import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

//    @Lob
    @Column(length = 1000000000)
    private byte[] picByte;

    @ManyToMany(mappedBy = "productImages")
    private List<Product> products;

    public ImageModel(String name, String type, byte[] picBytes) {
        this.name = name;
        this.type = type;
        this.picByte = picBytes;
    }
}
