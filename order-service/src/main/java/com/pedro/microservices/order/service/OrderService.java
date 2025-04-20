package com.pedro.microservices.order.service;

import com.pedro.microservices.order.client.InventoryClient;
import com.pedro.microservices.order.dto.OrderRequest;
import com.pedro.microservices.order.mapper.OrderMapper;
import com.pedro.microservices.order.model.Order;
import com.pedro.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest) {
        boolean inStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if(inStock) {
        Order order = orderMapper.toOrder(orderRequest);
        orderRepository.save(order);
        } else {
            throw new RuntimeException("Product with Skucode " + orderRequest.skuCode() + " is not in stock");
        }

    }
}
