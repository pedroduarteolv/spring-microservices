package com.pedro.microservices.order.mapper;

import com.pedro.microservices.order.dto.OrderRequest;
import com.pedro.microservices.order.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderRequest toOrderRequest(Order order);
    Order toOrder(OrderRequest orderRequest);
}
