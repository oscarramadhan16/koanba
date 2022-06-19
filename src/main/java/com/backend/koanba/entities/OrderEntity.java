package com.backend.koanba.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "order", schema = "koanba")
public class OrderEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(name = "customer_name", columnDefinition = "VARCHAR")
    private String customerName;

    @Column(name = "amount", columnDefinition = "DECIMAL", precision = 20, scale = 2, nullable = false)
    private double amount;

    @Column(name = "quantity", columnDefinition = "INT", nullable = false)
    private int quantity;

    @CreationTimestamp
    @Column(name = "order_date", columnDefinition = "TIMESTAMP", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    public OrderEntity(UUID id, Date createdAt, Date updatedAt, CustomerEntity customerEntity, ProductEntity productEntity, String customerName, double amount, int quantity, Date orderDate) {
        super(id, createdAt, updatedAt);
        this.customer = customerEntity;
        this.product = productEntity;
        this.customerName = customerName;
        this.amount = amount;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customerEntity) {
        this.customer = customerEntity;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity productEntity) {
        this.product = productEntity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
