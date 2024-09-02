package com.artisanat_backend.service;

import com.artisanat_backend.model.*;
import com.artisanat_backend.repository.SubOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SubOrderService {

    @Autowired
    private SubOrderRepository subOrderRepository;

    @Autowired
    private ArtisanService artisanService;

    @Autowired
    private NotificationService notificationService;

    public void createSubOrderForSingleProduct(Order mainOrder) {
        SubOrder subOrder = new SubOrder();
        subOrder.setArtisan(mainOrder.getProducts().getFirst().getArtisan());
        subOrder.setProducts(mainOrder.getProducts()); // Une liste avec un seul produit
        subOrder.setSubTotal(mainOrder.getProducts().getFirst().getPrice());
        subOrder.setOrder(mainOrder);

        subOrderRepository.save(subOrder);

        notificationService.notifyArtisan(mainOrder.getProducts().getFirst().getArtisan().getId(), subOrder);
    }

    public void createSubOrders(Order mainOrder) {
        Map<Artisan, List<Product>> productsByArtisan = mainOrder.getProducts().stream()
                .collect(Collectors.groupingBy(Product::getArtisan));

        for (Map.Entry<Artisan, List<Product>> entry : productsByArtisan.entrySet()) {
            SubOrder subOrder = new SubOrder();
            subOrder.setArtisan(entry.getKey());
            subOrder.setProducts(entry.getValue());
            subOrder.setSubTotal(entry.getValue().stream().mapToDouble(Product::getPrice).sum());
            subOrder.setOrder(mainOrder);

            subOrderRepository.save(subOrder);

            notificationService.notifyArtisan(entry.getKey().getId(), subOrder);
        }
    }

//    public void createSubOrders(Order mainOrder) {
//        // Récupérer la liste des produits depuis la commande principale
//        List<Product> products = mainOrder.getProducts();
//
//        // Créer une liste pour stocker les sous-commandes
//        List<SubOrder> subOrders = new ArrayList<>();
//
//        // Parcourir les produits pour les regrouper par artisan
//        for (Product product : products) {
//            Artisan artisan = product.getArtisan();
//            SubOrder existingSubOrder = findSubOrderByArtisan(subOrders, artisan);
//
//            if (existingSubOrder == null) {
//                // Créer une nouvelle sous-commande pour cet artisan
//                SubOrder newSubOrder = new SubOrder();
//                newSubOrder.setArtisan(artisan);
//                newSubOrder.setProducts(new ArrayList<>());
//                newSubOrder.setOrder(mainOrder);
//                subOrders.add(newSubOrder);
//                existingSubOrder = newSubOrder;
//            }
//
//            // Ajouter le produit à la sous-commande existante ou nouvelle
//            existingSubOrder.getProducts().add(product);
//            existingSubOrder.setSubTotal(existingSubOrder.getProducts().stream()
//                    .mapToDouble(Product::getPrice)
//                    .sum());
//        }
//
//        // Sauvegarder toutes les sous-commandes dans la base de données
//        for (SubOrder subOrder : subOrders) {
//            subOrderRepository.save(subOrder);
//            notificationService.notifyArtisan(subOrder.getArtisan().getId(), subOrder);
//        }
//    }

    // Méthode utilitaire pour trouver une sous-commande existante par artisan
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
