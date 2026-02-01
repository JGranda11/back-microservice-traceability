package com.pragma.challenge.msvc_traceability.domain.usecase;

import com.pragma.challenge.msvc_traceability.domain.api.OrderLogServicePort;
import com.pragma.challenge.msvc_traceability.domain.exception.EntityAlreadyExistsException;
import com.pragma.challenge.msvc_traceability.domain.exception.EntityNotFoundException;
import com.pragma.challenge.msvc_traceability.domain.exception.OrderLogAlreadyHasThatStateException;
import com.pragma.challenge.msvc_traceability.domain.exception.OrderLogAlreadyRegistersAnEmployeeException;
import com.pragma.challenge.msvc_traceability.domain.model.OrderLog;
import com.pragma.challenge.msvc_traceability.domain.model.OrderStateLog;
import com.pragma.challenge.msvc_traceability.domain.spi.OrderLogPersistencePort;
import com.pragma.challenge.msvc_traceability.domain.util.enums.OrderState;

import java.time.LocalDateTime;
import java.util.List;

public class OrderLogUseCase implements OrderLogServicePort {
    private final OrderLogPersistencePort orderLogPersistencePort;

    public OrderLogUseCase(OrderLogPersistencePort orderLogPersistencePort) {
        this.orderLogPersistencePort = orderLogPersistencePort;
    }


    @Override
    public OrderLog saveOrderLog(OrderLog log) {

        if (orderLogPersistencePort.findByOrderId(log.getOrderId()) != null)
            throw new EntityAlreadyExistsException(OrderLog.class.getSimpleName(), log.getOrderId().toString());

        OrderStateLog stateLog = OrderStateLog.builder()
                .state(OrderState.WAITING)
                .timestamp(LocalDateTime.now())
                .build();
        log.setStates(List.of(stateLog));

        return orderLogPersistencePort.saveOrderLog(log);
    }

    @Override
    public OrderLog addNewStateToOrderLog(Long orderId, OrderState state) {
        OrderLog log = getOrderLog(orderId);
        if (log.getStates().stream().anyMatch(stateLog -> stateLog.getState().equals(state)))
            throw new OrderLogAlreadyHasThatStateException();

        log.getStates().add(
                OrderStateLog.builder()
                        .state(state)
                        .timestamp(LocalDateTime.now())
                        .build()
        );

        return orderLogPersistencePort.saveOrderLog(log);
    }

    @Override
    public OrderLog addEmployeeToOrderLog(Long orderId, String assignedEmployeeId) {
        OrderLog log = getOrderLog(orderId);
        if(log.getAssignedEmployeeId() != null)
            throw new OrderLogAlreadyRegistersAnEmployeeException();
        log.setAssignedEmployeeId(assignedEmployeeId);

        log.getStates().add(
                OrderStateLog.builder()
                        .state(OrderState.PREPARING)
                        .timestamp(LocalDateTime.now())
                        .build()
        );

        return orderLogPersistencePort.saveOrderLog(log);
    }

    private OrderLog getOrderLog(Long orderId) {
        OrderLog log = orderLogPersistencePort.findByOrderId(orderId);
        if (log == null) throw new EntityNotFoundException(OrderLog.class.getSimpleName(), orderId.toString());
        return log;
    }
}
