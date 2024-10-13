package com.artisanat_backend.service;

import com.artisanat_backend.model.*;
import com.artisanat_backend.enums.Status;
import com.artisanat_backend.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private LoyaltyService loyaltyService;

    @Mock
    private CartService cartService;

    @Mock
    private SubOrderService subOrderService;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllOrders() {
        Order order1 = new Order();
        Order order2 = new Order();
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<Order> orders = orderService.getAllOrders();

        assertNotNull(orders);
        assertEquals(2, orders.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void getOrderById() {
        Order order = new Order();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Optional<Order> foundOrder = orderService.getOrderById(1L);

        assertTrue(foundOrder.isPresent());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void getOrdersByCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        Order order = new Order();
        when(orderRepository.findByCustomerId(customer.getId())).thenReturn(Arrays.asList(order));

        List<Order> orders = orderService.getOrdersByCustomer(customer);

        assertNotNull(orders);
        assertEquals(1, orders.size());
        verify(orderRepository, times(1)).findByCustomerId(customer.getId());
    }

    @Test
    void getOrdersByStatus() {
        Order order = new Order();
        when(orderRepository.findByStatus(Status.PENDING)).thenReturn(Arrays.asList(order));

        List<Order> orders = orderService.getOrdersByStatus(Status.PENDING);

        assertNotNull(orders);
        assertEquals(1, orders.size());
        verify(orderRepository, times(1)).findByStatus(Status.PENDING);
    }

    @Test
    void applyDiscountAndFinalizeOrder() {
        Customer customer = new Customer();
        customer.setId(1L);

        Order order = new Order();
        order.setTotalAmount(500.0);
        order.setCustomer(customer);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(loyaltyService.calculateDiscount(50, 500.0)).thenReturn(50.0);

        orderService.applyDiscountAndFinalizeOrder(1L, 50);

        assertEquals(450.0, order.getTotalAmount());
        verify(orderRepository, times(1)).save(order);
        verify(loyaltyService, times(1)).updateLoyaltyPoints(customer.getId(), 50);
    }

    @Test
    void deleteOrder() {
        orderService.deleteOrder(1L);

        verify(orderRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateOrderStatus() {
        Order order = new Order();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Status newStatus = Status.DELIVERED;
        Order updatedOrder = orderService.updateOrderStatus(1L, newStatus);

        assertEquals(newStatus, updatedOrder.getStatus());
        verify(orderRepository, times(1)).save(order);
    }
}
