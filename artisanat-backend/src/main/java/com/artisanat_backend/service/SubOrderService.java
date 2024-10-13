package com.artisanat_backend.service;

import com.artisanat_backend.model.*;
import com.artisanat_backend.repository.SubOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubOrderService {

    private final SubOrderRepository subOrderRepository;
    private final SubOrderItemService subOrderItemService;

    @Autowired
    public SubOrderService(SubOrderRepository subOrderRepository, SubOrderItemService subOrderItemService) {
        this.subOrderRepository = subOrderRepository;
        this.subOrderItemService = subOrderItemService;
    }

    public List<SubOrder> createSubOrders(Order order, List<CartItem> cartItems) {
        Map<Artisan, List<CartItem>> artisanCartItemsMap = new HashMap<>();

        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            Artisan artisan = product.getArtisan();
            artisanCartItemsMap.computeIfAbsent(artisan, k -> new ArrayList<>()).add(cartItem);
        }

        List<SubOrder> subOrders = new ArrayList<>();

        for (Map.Entry<Artisan, List<CartItem>> entry : artisanCartItemsMap.entrySet()) {
            SubOrder subOrder = new SubOrder();
            subOrder.setOrder(order);
            subOrder.setArtisan(entry.getKey());

            double subTotal = entry.getValue().stream()
                    .mapToDouble(cartItem -> cartItem.getQuantity() * cartItem.getProduct().getPrice())
                    .sum();
            subOrder.setSubTotal(subTotal);

            subOrderRepository.save(subOrder);

            List<SubOrderItem> subOrderItems = subOrderItemService.createSubOrderItems(subOrder, entry.getValue());
            subOrder.setSubOrderItems(subOrderItems);

            subOrderRepository.save(subOrder);
            subOrders.add(subOrder);
        }

        return subOrders;
    }

    private SubOrder findSubOrderByArtisan(List<SubOrder> subOrders, Artisan artisan) {
        for (SubOrder subOrder : subOrders) {
            if (subOrder.getArtisan().equals(artisan)) {
                return subOrder;
            }
        }
        return null;
    }


    public List<SubOrder> getSubOrdersByOrderId(Long orderId) {
        return subOrderRepository.findByOrderId(orderId);
    }

    public void deleteSubOrder(Long subOrderId) {
        subOrderRepository.deleteById(subOrderId);
    }
}
