package com.pragma.challenge.msvc_traceability.application.handler.impl;

import com.pragma.challenge.msvc_traceability.application.dto.request.NewOrderLogRequest;
import com.pragma.challenge.msvc_traceability.application.dto.response.ShortOrderLogResponse;
import com.pragma.challenge.msvc_traceability.application.handler.OrderLogHandler;
import com.pragma.challenge.msvc_traceability.application.mapper.request.OrderLogRequestMapper;
import com.pragma.challenge.msvc_traceability.application.mapper.response.OrderLogResponseMapper;
import com.pragma.challenge.msvc_traceability.domain.api.OrderLogServicePort;
import com.pragma.challenge.msvc_traceability.domain.model.log.OrderLog;
import com.pragma.challenge.msvc_traceability.domain.util.enums.OrderState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLogHandlerImpl implements OrderLogHandler {

    private final OrderLogServicePort orderLogServicePort;
    private final OrderLogRequestMapper orderLogRequestMapper;
    private final OrderLogResponseMapper orderLogResponseMapper;
    @Override
    public ShortOrderLogResponse saveNewOrderLog(NewOrderLogRequest request) {
        OrderLog log = orderLogRequestMapper.toDomain(request);

        return orderLogResponseMapper.toResponse(
                orderLogServicePort.saveOrderLog(log)
        );
    }

    @Override
    public ShortOrderLogResponse addNewStateToOrder(Long orderId, OrderState state) {
        return orderLogResponseMapper.toResponse(
                orderLogServicePort.addNewStateToOrderLog(orderId,state)
        );
    }

    @Override
    public ShortOrderLogResponse addEmployeeToOrder(Long orderId, String employeeId) {
        return orderLogResponseMapper.toResponse(
                orderLogServicePort.addEmployeeToOrderLog(orderId, employeeId)
        );
    }
}
