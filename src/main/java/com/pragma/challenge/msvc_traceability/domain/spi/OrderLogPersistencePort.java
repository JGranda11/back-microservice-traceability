package com.pragma.challenge.msvc_traceability.domain.spi;

import com.pragma.challenge.msvc_traceability.domain.model.log.OrderLog;

import java.util.List;

public interface OrderLogPersistencePort {
    OrderLog saveOrderLog(OrderLog log);
    OrderLog findByOrderId(Long orderId);
    List<OrderLog> findRestaurantOrders(String restaurantId);
}
