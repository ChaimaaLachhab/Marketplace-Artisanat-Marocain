package com.artisanat_backend.service;

import com.artisanat_backend.entity.Product;
import com.artisanat_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private ProductRepository productRepository;

}
