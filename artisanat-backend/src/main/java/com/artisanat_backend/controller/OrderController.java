package com.artisanat_backend.controller;

import com.artisanat_backend.dto.request.OrderRequestDto;
import com.artisanat_backend.dto.response.OrderResponseDto;
import com.artisanat_backend.enums.Status;
import com.artisanat_backend.mapper.OrderMapper;
import com.artisanat_backend.model.Admin;
import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.Order;
import com.artisanat_backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper; // Inject OrderMapper

    /**
     * Récupère toutes les commandes.
     * @return Liste des commandes.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        List<OrderResponseDto> orderResponseDtos = orders.stream()
                .map(orderMapper::toDto) // Map to DTO
                .collect(Collectors.toList());
        return new ResponseEntity<>(orderResponseDtos, HttpStatus.OK);
    }

    /**
     * Récupère une commande par son ID.
     * @param id ID de la commande.
     * @return Commande demandée.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(orderMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Récupère les commandes d'un client authentifié.
     * @param customer L'utilisateur authentifié.
     * @return Liste des commandes du client.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/customer")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByCustomer(@AuthenticationPrincipal Customer customer) {
        List<Order> orders = orderService.getOrdersByCustomer(customer);
        List<OrderResponseDto> orderResponseDtos = orders.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(orderResponseDtos, HttpStatus.OK);
    }

    /**
     * Récupère les commandes par statut.
     * @param status Statut des commandes.
     * @return Liste des commandes avec le statut spécifié.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByStatus(@PathVariable Status status) {
        try {
            List<Order> orders = orderService.getOrdersByStatus(status);
            List<OrderResponseDto> orderResponseDtos = orders.stream()
                    .map(orderMapper::toDto)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(orderResponseDtos, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @PostMapping("/create")
    public ResponseEntity<OrderResponseDto> createOrder(@AuthenticationPrincipal Customer customer, @RequestParam String location, @RequestParam(defaultValue = "0") int point) {
        Order order = orderService.processOrder(customer, location, point );
        return new ResponseEntity<>(orderMapper.toDto(order), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @PutMapping("/update")
    public ResponseEntity<OrderResponseDto> updateOrderStatus(@RequestParam Long orderId, @RequestParam Status status) {
        Order order = orderService.updateOrderStatus(orderId, status);
        return new ResponseEntity<>(orderMapper.toDto(order), HttpStatus.CREATED);
    }
}
