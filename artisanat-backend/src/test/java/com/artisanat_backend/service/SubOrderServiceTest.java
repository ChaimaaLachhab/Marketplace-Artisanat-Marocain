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
    private SubOrderItemService subOrderItemService;

    @InjectMocks
    private SubOrderService subOrderService;

    private Order mainOrder;
    private Artisan artisan;
    private Product product;
    private CartItem cartItem;
    private List<CartItem> cartItems;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        artisan = new Artisan();
        artisan.setId(1L);
        artisan.setFullName("Artisan 1");

        product = new Product();
        product.setId(1L);
        product.setPrice(100.0F);
        product.setArtisan(artisan);

        cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(2);

        cartItems = List.of(cartItem);

        mainOrder = new Order();
        mainOrder.setId(1L);
    }

    @Test
    void createSubOrders_SavesSubOrdersAndCallsSubOrderItemService() {
        SubOrder subOrder = new SubOrder();
        subOrder.setOrder(mainOrder);
        subOrder.setArtisan(artisan);

        List<SubOrderItem> subOrderItems = new ArrayList<>();
        when(subOrderItemService.createSubOrderItems(any(SubOrder.class), anyList())).thenReturn(subOrderItems);

        List<SubOrder> result = subOrderService.createSubOrders(mainOrder, cartItems);

        verify(subOrderRepository, times(2)).save(any(SubOrder.class));
        verify(subOrderItemService, times(1)).createSubOrderItems(any(SubOrder.class), anyList());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(artisan, result.get(0).getArtisan());
    }

    @Test
    void getSubOrdersByOrderId_ReturnsSubOrders() {
        SubOrder subOrder = new SubOrder();
        subOrder.setOrder(mainOrder);
        subOrder.setArtisan(artisan);

        when(subOrderRepository.findByOrderId(mainOrder.getId())).thenReturn(List.of(subOrder));

        List<SubOrder> result = subOrderService.getSubOrdersByOrderId(mainOrder.getId());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(subOrder, result.get(0));

        verify(subOrderRepository, times(1)).findByOrderId(mainOrder.getId());
    }

    @Test
    void deleteSubOrder_DeletesSubOrder() {
        Long subOrderId = 1L;

        subOrderService.deleteSubOrder(subOrderId);

        verify(subOrderRepository, times(1)).deleteById(subOrderId);
    }
}
