package com.backend.koanba.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customer", schema = "koanba")
public class CustomerEntity extends BaseEntity {
    @Column(name = "name", columnDefinition = "VARCHAR", nullable = false)
    private String name;

    @Column(name = "address", columnDefinition = "TEXT", nullable = false)
    private String address;

    @Column(name = "phone", columnDefinition = "VARCHAR", length = 16, nullable = false)
    private String phone;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderEntity> orders;

    public CustomerEntity(UUID id, Date createdAt, Date updatedAt, String name, String address, String phone) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
