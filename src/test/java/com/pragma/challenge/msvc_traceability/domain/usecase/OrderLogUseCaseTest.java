package com.pragma.challenge.msvc_traceability.domain.usecase;

import com.pragma.challenge.msvc_traceability.domain.exception.EntityAlreadyExistsException;
import com.pragma.challenge.msvc_traceability.domain.exception.EntityNotFoundException;
import com.pragma.challenge.msvc_traceability.domain.exception.OrderLogAlreadyHasThatStateException;
import com.pragma.challenge.msvc_traceability.domain.exception.OrderLogAlreadyRegistersAnEmployeeException;
import com.pragma.challenge.msvc_traceability.domain.model.OrderLog;
import com.pragma.challenge.msvc_traceability.domain.model.OrderStateLog;
import com.pragma.challenge.msvc_traceability.domain.spi.OrderLogPersistencePort;
import com.pragma.challenge.msvc_traceability.domain.util.enums.OrderState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderLogUseCaseTest {

    @Mock
    private OrderLogPersistencePort orderLogPersistencePort;

    private OrderLogUseCase orderLogUseCase;

    private static final Long ORDER_ID = 1L;
    private static final String CUSTOMER_ID = "CUSTOMER_123";
    private static final String RESTAURANT_ID = "RESTAURANT_456";
    private static final String EMPLOYEE_ID = "EMPLOYEE_789";

    @BeforeEach
    void setUp() {
        orderLogUseCase = new OrderLogUseCase(orderLogPersistencePort);
    }

    @Test
    void shouldSaveOrderLogSuccessfully() {
        // Given
        OrderLog orderLog = OrderLog.builder()
                .orderId(ORDER_ID)
                .customerId(CUSTOMER_ID)
                .restaurantId(RESTAURANT_ID)
                .build();

        when(orderLogPersistencePort.findByOrderId(ORDER_ID)).thenReturn(null);
        when(orderLogPersistencePort.saveOrderLog(any(OrderLog.class))).thenReturn(orderLog);

        // When
        OrderLog result = orderLogUseCase.saveOrderLog(orderLog);

        // Then
        assertNotNull(result.getStates());
        assertEquals(OrderState.WAITING, result.getStates().getFirst().getState());
        verify(orderLogPersistencePort, times(1)).saveOrderLog(any(OrderLog.class));
    }

    @Test
    void shouldThrowEntityAlreadyExistsException_WhenOrderLogExists() {
        // Given
        OrderLog existingOrderLog = OrderLog.builder().orderId(ORDER_ID).build();
        when(orderLogPersistencePort.findByOrderId(ORDER_ID)).thenReturn(existingOrderLog);

        // When & Then
        assertThrows(EntityAlreadyExistsException.class, () -> orderLogUseCase.saveOrderLog(existingOrderLog));
    }

    @Test
    void shouldThrowOrderLogAlreadyHasThatStateException_WhenStateAlreadyExists() {
        // Given
        OrderLog existingOrderLog = OrderLog.builder()
                .orderId(ORDER_ID)
                .states(List.of(OrderStateLog.builder().state(OrderState.WAITING).build()))
                .build();

        when(orderLogPersistencePort.findByOrderId(ORDER_ID)).thenReturn(existingOrderLog);

        // When & Then
        assertThrows(OrderLogAlreadyHasThatStateException.class, () -> orderLogUseCase.addNewStateToOrderLog(ORDER_ID, OrderState.WAITING));
    }

    @Test
    void shouldThrowEntityNotFoundException_WhenAddingStateToNonExistingOrderLog() {
        when(orderLogPersistencePort.findByOrderId(ORDER_ID)).thenReturn(null);

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> orderLogUseCase.addNewStateToOrderLog(ORDER_ID, OrderState.DONE));
    }


    @Test
    void shouldThrowOrderLogAlreadyRegistersAnEmployeeException_WhenEmployeeAlreadyAssigned() {
        // Given
        OrderLog existingOrderLog = OrderLog.builder()
                .orderId(ORDER_ID)
                .assignedEmployeeId(EMPLOYEE_ID) // Already assigned
                .build();

        when(orderLogPersistencePort.findByOrderId(ORDER_ID)).thenReturn(existingOrderLog);

        // When & Then
        assertThrows(OrderLogAlreadyRegistersAnEmployeeException.class, () -> orderLogUseCase.addEmployeeToOrderLog(ORDER_ID, EMPLOYEE_ID));
    }

    @Test
    void shouldThrowEntityNotFoundException_WhenAddingEmployeeToNonExistingOrderLog() {
        when(orderLogPersistencePort.findByOrderId(ORDER_ID)).thenReturn(null);

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> orderLogUseCase.addEmployeeToOrderLog(ORDER_ID, EMPLOYEE_ID));
    }
}