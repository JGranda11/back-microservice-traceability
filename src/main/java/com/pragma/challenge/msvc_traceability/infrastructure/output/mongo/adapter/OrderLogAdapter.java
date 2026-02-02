package com.pragma.challenge.msvc_traceability.infrastructure.output.mongo.adapter;

import com.pragma.challenge.msvc_traceability.domain.model.log.OrderLog;
import com.pragma.challenge.msvc_traceability.domain.spi.OrderLogPersistencePort;
import com.pragma.challenge.msvc_traceability.infrastructure.output.mongo.entity.OrderLogEntity;
import com.pragma.challenge.msvc_traceability.infrastructure.output.mongo.mapper.OrderLogEntityMapper;
import com.pragma.challenge.msvc_traceability.infrastructure.output.mongo.repository.OrderLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderLogAdapter implements OrderLogPersistencePort {
    private final OrderLogRepository orderLogRepository;
    private final OrderLogEntityMapper orderLogEntityMapper;

    @Override
    public OrderLog saveOrderLog(OrderLog log) {
        OrderLogEntity entity = orderLogEntityMapper.toEntity(log);
        return orderLogEntityMapper.toDomain(
                orderLogRepository.save(entity)
        );
    }

    @Override
    public OrderLog findByOrderId(Long orderId) {
        return orderLogEntityMapper.toDomain(
                orderLogRepository.findByOrderId(orderId)
        );
    }

    @Override
    public List<OrderLog> findRestaurantOrders(String restaurantId) {
        return orderLogEntityMapper.toDomains(
                orderLogRepository.findByRestaurantId(restaurantId)
        );
    }
}
