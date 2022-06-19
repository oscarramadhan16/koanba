package com.backend.koanba.services;

import com.backend.koanba.controllers.request.OrderRequest;
import com.backend.koanba.entities.CustomerEntity;
import com.backend.koanba.entities.OrderEntity;
import com.backend.koanba.entities.ProductEntity;
import com.backend.koanba.exceptions.CustomerNotFoundException;
import com.backend.koanba.exceptions.ProductNotFoundException;
import com.backend.koanba.exceptions.ProductQuantityInsufficientException;
import com.backend.koanba.repositories.OrderRepository;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CustomerService customerService;

    public OrderService(OrderRepository orderRepository, ProductService productService, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.customerService = customerService;
    }

    public OrderEntity create(OrderRequest orderRequest) throws ProductNotFoundException, CustomerNotFoundException, ProductQuantityInsufficientException, ObjectOptimisticLockingFailureException {
        ProductEntity product = this.productService.get(orderRequest.getProductId());
        if (orderRequest.getQuantity() > product.getStock()) {
            throw new ProductQuantityInsufficientException("existing product quantity is insufficient");
        }
        CustomerEntity customer = this.customerService.get(orderRequest.getCustomerId());
        product.setStock(product.getStock() - orderRequest.getQuantity());

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(UUID.randomUUID());
        orderEntity.setCustomerName(customer.getName());
        orderEntity.setCustomer(customer);
        orderEntity.setProduct(product);
        orderEntity.setAmount(orderRequest.getAmount());
        orderEntity.setQuantity(orderRequest.getQuantity());

        return this.orderRepository.save(orderEntity);
    }
}
