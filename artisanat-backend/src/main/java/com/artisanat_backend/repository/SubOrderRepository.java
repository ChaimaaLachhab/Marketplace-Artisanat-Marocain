package com.artisanat_backend.repository;

import com.artisanat_backend.enums.Status;
import com.artisanat_backend.model.Order;
import com.artisanat_backend.model.SubOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubOrderRepository extends JpaRepository<SubOrder, Long> {
    List<SubOrder> findByOrderId(Long orderId);
}
