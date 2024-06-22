package com.example.L2.S2.Project.controller;

import com.example.L2.S2.Project.dao.request.OrderRequest;
import com.example.L2.S2.Project.model.Order;
import com.example.L2.S2.Project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    
    @PostMapping("/create")
    // @PreAuthorize("hasAnyAuthority('User')")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
        orderService.createOrder(orderRequest);
        return new ResponseEntity<>("Order created successfully", HttpStatus.OK);
    }

    @GetMapping("/all")
    // @PreAuthorize("hasAnyAuthority('Admin','User')")
    public List<Order> allOrders() throws IllegalStateException {
        return orderService.allOrders();
    }

}
