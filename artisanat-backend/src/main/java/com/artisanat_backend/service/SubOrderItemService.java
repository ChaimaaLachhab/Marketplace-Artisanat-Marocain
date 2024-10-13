package com.artisanat_backend.service;

import com.artisanat_backend.model.*;
import com.artisanat_backend.repository.SubOrderItemRepository;
import com.artisanat_backend.repository.SubOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SubOrderItemService {

    private final SubOrderItemRepository subOrderItemRepository;

    @Autowired
    public SubOrderItemService(SubOrderItemRepository subOrderItemRepository, ArtisanService artisanService, NotificationService notificationService) {
        this.subOrderItemRepository = subOrderItemRepository;
    }

    public List<SubOrderItem> createSubOrderItems(SubOrder subOrder, List<CartItem> cartItems) {
        System.out.println("Creating SubOrderItems for SubOrder ID: " + subOrder.getId());

        List<SubOrderItem> subOrderItems = cartItems.stream().map(cartItem -> {
            SubOrderItem subOrderItem = new SubOrderItem();
            subOrderItem.setSubOrder(subOrder);
            subOrderItem.setProduct(cartItem.getProduct());
            subOrderItem.setQuantity(cartItem.getQuantity());

            if (cartItem.getProduct() == null) {
                throw new IllegalArgumentException("Product cannot be null for cart item ID: " + cartItem.getId());
            }

            System.out.println("SubOrderItem created for product ID: " + cartItem.getProduct().getId() +
                    " with quantity: " + cartItem.getQuantity());

            return subOrderItem;
        }).collect(Collectors.toList());

        return subOrderItemRepository.saveAll(subOrderItems);
    }


}
