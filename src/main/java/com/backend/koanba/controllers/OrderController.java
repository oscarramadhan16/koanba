package com.backend.koanba.controllers;

import com.backend.koanba.controllers.request.OrderRequest;
import com.backend.koanba.entities.OrderEntity;
import com.backend.koanba.exceptions.CustomerNotFoundException;
import com.backend.koanba.exceptions.ProductNotFoundException;
import com.backend.koanba.exceptions.ProductQuantityInsufficientException;
import com.backend.koanba.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //  create order
    @PostMapping
    public ResponseEntity<OrderEntity> create(
            @RequestBody OrderRequest orderRequest
    ) throws ProductNotFoundException, CustomerNotFoundException, ProductQuantityInsufficientException, ObjectOptimisticLockingFailureException {
        return ResponseEntity.ok(this.orderService.create(orderRequest));
    }
}
