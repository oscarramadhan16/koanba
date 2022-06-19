package com.backend.koanba.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private String customerId;
    private String productId;
    private int quantity;
    private double amount;
}
