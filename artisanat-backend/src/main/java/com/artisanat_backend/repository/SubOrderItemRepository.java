package com.artisanat_backend.repository;

import com.artisanat_backend.model.SubOrder;
import com.artisanat_backend.model.SubOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubOrderItemRepository extends JpaRepository<SubOrderItem, Long> {}
