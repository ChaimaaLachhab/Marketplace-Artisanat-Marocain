package com.artisanat_backend.repository;

import com.artisanat_backend.model.Media;
import com.artisanat_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {}
