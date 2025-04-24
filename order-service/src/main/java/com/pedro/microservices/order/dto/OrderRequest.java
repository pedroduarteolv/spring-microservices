package com.pedro.microservices.order.dto;

import java.math.BigDecimal;

public record OrderRequest(Long id, String skuCode, BigDecimal price, Integer quantity, UserDetails userDetails) {

    public record UserDetails(String firstName, String email, String lastName) {
    }
}
