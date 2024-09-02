package com.artisanat_backend.service;

import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.Order;
import com.artisanat_backend.model.Product;
import com.artisanat_backend.repository.OrderRepository;
import com.artisanat_backend.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private LoyaltyService loyaltyService;

    @Autowired
    private CartService cartService;

    @Autowired
    private SubOrderService subOrderService;

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
     * Crée une commande pour un produit unique.
     * @param customer Client qui passe la commande.
     * @param order Commande contenant un produit unique.
     * @return La commande créée.
     */
    public Order createOrderForSingleProduct(Customer customer, Order order) {
        // Sauvegarder la commande principale
        Order mainOrder = orderRepository.save(order);

        // Calculer et ajouter les points de fidélité
        int earnedPoints = loyaltyService.calculatePoints(order);
        loyaltyService.addPointsToCustomerLoyalty(customer, earnedPoints);

        // Créer la sous-commande pour le produit unique
        subOrderService.createSubOrderForSingleProduct(mainOrder);
        return mainOrder;
    }

    /**
     * Crée une commande avec plusieurs produits.
     * @param customer Client qui passe la commande.
     * @param mainOrder Commande contenant plusieurs produits.
     * @return La commande créée.
     */
    public Order createOrder(Customer customer, Order mainOrder) {
        // Calculer le total basé sur les produits de la commande
        double totalOrderValue = mainOrder.getProducts().stream()
                .mapToDouble(Product::getPrice)
                .sum();

        mainOrder.setCustomer(customer);
        mainOrder.setTotalAmount(totalOrderValue);

        // Sauvegarder la commande principale
        orderRepository.save(mainOrder);

        // Calculer et ajouter les points de fidélité
        int earnedPoints = loyaltyService.calculatePoints(mainOrder);
        loyaltyService.addPointsToCustomerLoyalty(customer, earnedPoints);

        // Créer les sous-commandes pour les produits
        subOrderService.createSubOrders(mainOrder);

        return mainOrder;
    }

    /**
     * Applique une réduction basée sur les points de fidélité et finalise la commande.
     * @param orderId ID de la commande.
     * @param loyaltyPoints Points de fidélité utilisés.
     */
    public void applyDiscountAndFinalizeOrder(Long orderId, int loyaltyPoints) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Calculer la réduction basée sur les points
        double discount = loyaltyService.calculateDiscount(loyaltyPoints, order.getTotalAmount());
        order.setTotalAmount(order.getTotalAmount() - discount);

        // Sauvegarder la commande avec la réduction appliquée
        orderRepository.save(order);

        // Mettre à jour les points de fidélité du client
        loyaltyService.updateLoyaltyPoints(order.getCustomer().getId(), loyaltyPoints);
    }

    /**
     * Supprime une commande par son ID.
     * @param id ID de la commande.
     */
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
