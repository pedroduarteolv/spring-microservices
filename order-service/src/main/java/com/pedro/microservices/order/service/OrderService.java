package com.pedro.microservices.order.service;

import com.pedro.microservices.order.client.InventoryClient;
import com.pedro.microservices.order.dto.OrderRequest;
import com.pedro.microservices.order.event.OrderPlacedEvent;
import com.pedro.microservices.order.mapper.OrderMapper;
import com.pedro.microservices.order.model.Order;
import com.pedro.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderRequest orderRequest) {
        boolean inStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if(inStock) {
        Order order = orderMapper.toOrder(orderRequest);
        order.setOrderNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();
            orderPlacedEvent.setOrderNumber(order.getOrderNumber());
            orderPlacedEvent.setEmail(orderRequest.userDetails().email());
            orderPlacedEvent.setFirstName(orderRequest.userDetails().firstName());
            orderPlacedEvent.setLastName(orderRequest.userDetails().lastName());

            log.info("Order placed event: {}", orderPlacedEvent);
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            log.info("Order placed event sent to Kafka: {}", orderPlacedEvent);
        } else {
            throw new RuntimeException("Product with Skucode " + orderRequest.skuCode() + " is not in stock");
        }

    }
}
