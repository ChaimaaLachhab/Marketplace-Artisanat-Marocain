package com.artisanat_backend.service;

import com.artisanat_backend.model.*;
import com.artisanat_backend.repository.OrderRepository;
import com.artisanat_backend.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final LoyaltyService loyaltyService;
    private final CartService cartService;
    private final SubOrderService subOrderService;
    private final NotificationService notificationService;

    @Autowired
    public OrderService(OrderRepository orderRepository, LoyaltyService loyaltyService, CartService cartService, SubOrderService subOrderService, NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.loyaltyService = loyaltyService;
        this.cartService = cartService;
        this.subOrderService = subOrderService;
        this.notificationService = notificationService;
    }

    /**
     * Récupère toutes les commandes.
     * @return Liste des commandes.
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Récupère une commande par son ID.
     * @param id ID de la commande.
     * @return Optionnel de la commande.
     */
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    /**
     * Récupère les commandes d'un client.
     * @param customer Client dont on veut les commandes.
     * @return Liste des commandes du client.
     */
    public List<Order> getOrdersByCustomer(Customer customer) {
        return orderRepository.findByCustomerId(customer.getId());
    }

    /**
     * Récupère les commandes par statut.
     * @param status Statut des commandes.
     * @return Liste des commandes avec le statut spécifié.
     */
    public List<Order> getOrdersByStatus(Status status) {
        return orderRepository.findByStatus(status);
    }

    /**
     * Crée une commande avec plusieurs produits.
     * @param customer Client qui passe la commande.
     * @return La commande créée.
     */
    public Order processOrder(Customer customer, String location, int usedPoints) {
        List<CartItem> cartItems = cartService.getCartItems(customer);

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Status.PENDING);
        order.setLocation(location);

        orderRepository.save(order);

        List<SubOrder> subOrders = subOrderService.createSubOrders(order, cartItems);

        double totalAmount = subOrders.stream()
                .mapToDouble(SubOrder::getSubTotal)
                .sum();

        int earnedPoints = loyaltyService.calculatePoints(totalAmount);
        loyaltyService.addPointsToCustomerLoyalty(customer, earnedPoints);

        double discount = 0.0;
        if (usedPoints > 0) {
            Loyalty loyalty = customer.getLoyalty();
            if (loyalty != null && loyalty.getPoints() >= usedPoints) {
                discount = loyaltyService.calculateDiscount(usedPoints, totalAmount);
                loyaltyService.updateLoyaltyPoints(customer.getId(), usedPoints);
            } else {
                throw new IllegalArgumentException("Not enough loyalty points.");
            }
        }
        double finalAmount = totalAmount - discount;
        order.setTotalAmount(finalAmount);
        order.setSubOrders(subOrders);

        Order finalOrder = orderRepository.save(order);

        for (SubOrder subOrder : subOrders) {
            Artisan artisan = subOrder.getArtisan();
            notificationService.notifyArtisan(artisan, subOrder);
        }

        cartService.clearCart(customer.getId());

        return finalOrder;
    }


    /**
     * Applique une réduction basée sur les points de fidélité et finalise la commande.
     * @param orderId ID de la commande.
     * @param loyaltyPoints Points de fidélité utilisés.
     */
    public void applyDiscountAndFinalizeOrder(Long orderId, int loyaltyPoints) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        double discount = loyaltyService.calculateDiscount(loyaltyPoints, order.getTotalAmount());
        order.setTotalAmount(order.getTotalAmount() - discount);

        orderRepository.save(order);

        loyaltyService.updateLoyaltyPoints(order.getCustomer().getId(), loyaltyPoints);
    }

    /**
     * Supprime une commande par son ID.
     * @param id ID de la commande.
     */
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    /**
     * Supprime une commande par son ID.
     * @param orderId ID de la commande.
     */
    public Order updateOrderStatus(Long orderId, Status status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);
        return order;
    }
}
