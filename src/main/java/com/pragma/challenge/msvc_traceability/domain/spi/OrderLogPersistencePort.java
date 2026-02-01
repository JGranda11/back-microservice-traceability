package com.pragma.challenge.msvc_traceability.domain.spi;

import com.pragma.challenge.msvc_traceability.domain.model.OrderLog;

public interface OrderLogPersistencePort {
    OrderLog saveOrderLog(OrderLog log);
    OrderLog findByOrderId(Long orderId);
}
