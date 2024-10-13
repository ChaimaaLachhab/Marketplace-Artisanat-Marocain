package com.artisanat_backend.model;

import com.artisanat_backend.enums.Type;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mediaUrl;
    private String mediaId;
    private Type type;

    @JsonIgnore
    @ManyToOne
    private Product product;

    @JsonIgnore
    @OneToOne
    private User user;
}
