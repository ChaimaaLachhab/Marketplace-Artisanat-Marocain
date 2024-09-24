package com.artisanat_backend.service;

import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.Product;
import com.artisanat_backend.model.Order;
import com.artisanat_backend.enums.Status;
import com.artisanat_backend.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    private SubOrderService subOrderService;

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
    void createOrderForSingleProduct() {
        Customer customer = new Customer();
        Order order = new Order();
        when(orderRepository.save(order)).thenReturn(order);

        orderService.createOrderForSingleProduct(customer, order);

        verify(orderRepository, times(1)).save(order);
        verify(loyaltyService, times(1)).calculatePoints(order);
        verify(subOrderService, times(1)).createSubOrderForSingleProduct(order);
    }

    @Test
    void createOrder() {
        Customer customer = new Customer();
        Order order = new Order();

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setPrice(100.0f);
        product1.setStock(10);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setPrice(200.0f);
        product2.setStock(5);

        order.setProducts(Arrays.asList(product1, product2));
        when(orderRepository.save(order)).thenReturn(order);

        Order createdOrder = orderService.createOrder(customer, order);

        assertEquals(300.0, createdOrder.getTotalAmount());
        verify(orderRepository, times(1)).save(order);
        verify(loyaltyService, times(1)).calculatePoints(order);
        verify(subOrderService, times(1)).createSubOrders(order);
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
        verify(loyaltyService, times(1)).updateLoyaltyPoints(customer.getId(), 50); // Use the customer object to get the ID
    }


    @Test
    void deleteOrder() {
        orderService.deleteOrder(1L);

        verify(orderRepository, times(1)).deleteById(1L);
    }

}
