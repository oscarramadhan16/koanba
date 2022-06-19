package com.backend.koanba.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product", schema = "koanba")
public class ProductEntity extends BaseEntity {
    @Version
    private Long version;

    @Column(name = "price", columnDefinition = "DECIMAL", precision = 20, scale = 2, nullable = false)
    private double price;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "stock", columnDefinition = "INT", nullable = false)
    private int stock;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderEntity> orders;

    public ProductEntity(UUID id, Date createdAt, Date updatedAt, Long version, double price, String description, int stock) {
        super(id, createdAt, updatedAt);
        this.version = version;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    public ProductEntity() {
        super();
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
