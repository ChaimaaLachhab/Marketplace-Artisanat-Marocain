package com.artisanat_backend.repository;

import com.artisanat_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

    @Query("SELECT p FROM Product p JOIN p.reviews r GROUP BY p.id ORDER BY AVG(r.rating) DESC")
    List<Product> findTopRatedProducts();

    @Query("SELECT p FROM Product p ORDER BY p.createdAt DESC")
    List<Product> findRecentlyAddedProducts();}
