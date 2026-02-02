package com.pragma.challenge.msvc_traceability.application.handler;

import com.pragma.challenge.msvc_traceability.application.dto.request.NewOrderLogRequest;
import com.pragma.challenge.msvc_traceability.application.dto.response.ShortOrderLogResponse;
import com.pragma.challenge.msvc_traceability.domain.util.enums.OrderState;

public interface OrderLogHandler {
    ShortOrderLogResponse saveNewOrderLog(NewOrderLogRequest request);
    ShortOrderLogResponse addNewStateToOrder(Long orderId, OrderState state);
    ShortOrderLogResponse addEmployeeToOrder(Long orderId, String employeeId);
}