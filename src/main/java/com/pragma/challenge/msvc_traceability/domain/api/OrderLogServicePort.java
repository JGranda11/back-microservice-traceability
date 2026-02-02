package com.pragma.challenge.msvc_traceability.domain.api;

import com.pragma.challenge.msvc_traceability.domain.model.log.OrderLog;
import com.pragma.challenge.msvc_traceability.domain.util.enums.OrderState;

public interface OrderLogServicePort {
    OrderLog saveOrderLog(OrderLog log);
    OrderLog addNewStateToOrderLog(Long orderId, OrderState state);
    OrderLog addEmployeeToOrderLog(Long orderId, String assignedEmployeeId);
}
