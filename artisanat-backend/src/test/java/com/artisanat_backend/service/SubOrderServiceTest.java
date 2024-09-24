package com.artisanat_backend.service;

import com.artisanat_backend.model.*;
import com.artisanat_backend.repository.SubOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubOrderServiceTest {

    @Mock
    private SubOrderRepository subOrderRepository;

    @Mock
    private ArtisanService artisanService;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private SubOrderService subOrderService;

    private Order mainOrder;
    private SubOrder subOrder;
    private Artisan artisan;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        artisan = new Artisan();
        artisan.setId(1L);
        artisan.setFullName("Artisan 1");

        Product product = new Product();
        product.setId(1L);
        product.setPrice(100.0F);
        product.setArtisan(artisan);

        mainOrder = new Order();
        mainOrder.setId(1L);
        mainOrder.setProducts(List.of(product));

        subOrder = new SubOrder();
        subOrder.setId(1L);
        subOrder.setArtisan(artisan);
        subOrder.setProducts(List.of(product));
        subOrder.setSubTotal(100.0);
        subOrder.setOrder(mainOrder);
    }

    @Test
    void createSubOrderForSingleProduct_SavesSubOrderAndNotifiesArtisan() {
        // Test de la création d'une sous-commande pour un seul produit
        subOrderService.createSubOrderForSingleProduct(mainOrder);

        // Vérifier que la sous-commande a été sauvegardée
        verify(subOrderRepository, times(1)).save(any(SubOrder.class));

        // Vérifier que la notification a été envoyée à l'artisan
        verify(notificationService, times(1)).notifyArtisan(eq(artisan.getId()), any(SubOrder.class));
    }

    @Test
    void createSubOrders_SavesMultipleSubOrdersAndNotifiesArtisans() {
        // Test de la création de sous-commandes pour plusieurs produits (par artisan)
        subOrderService.createSubOrders(mainOrder);

        // Vérifier que la sous-commande a été sauvegardée
        verify(subOrderRepository, times(1)).save(any(SubOrder.class));

        // Vérifier que la notification a été envoyée à l'artisan
        verify(notificationService, times(1)).notifyArtisan(eq(artisan.getId()), any(SubOrder.class));
    }

    @Test
    void getSubOrdersByOrderId_ReturnsSubOrders() {
        // Configuration du mock pour retourner une liste de sous-commandes
        when(subOrderRepository.findByOrderId(mainOrder.getId())).thenReturn(List.of(subOrder));

        List<SubOrder> result = subOrderService.getSubOrdersByOrderId(mainOrder.getId());

        // Vérifier que la liste de sous-commandes est retournée correctement
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(subOrder, result.get(0));

        // Vérifier que la méthode du repository a été appelée une fois
        verify(subOrderRepository, times(1)).findByOrderId(mainOrder.getId());
    }

    @Test
    void deleteSubOrder_DeletesSubOrder() {
        Long subOrderId = 1L;

        // Appeler la méthode de suppression
        subOrderService.deleteSubOrder(subOrderId);

        // Vérifier que la sous-commande a bien été supprimée
        verify(subOrderRepository, times(1)).deleteById(subOrderId);
    }
}
