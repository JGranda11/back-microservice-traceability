package com.pragma.challenge.msvc_traceability.domain.usecase;

import com.pragma.challenge.msvc_traceability.domain.model.Restaurant;
import com.pragma.challenge.msvc_traceability.domain.model.log.OrderLog;
import com.pragma.challenge.msvc_traceability.domain.model.log.OrderStateLog;
import com.pragma.challenge.msvc_traceability.domain.model.report.EmployeeReport;
import com.pragma.challenge.msvc_traceability.domain.model.report.OrderReport;
import com.pragma.challenge.msvc_traceability.domain.spi.OrderLogPersistencePort;
import com.pragma.challenge.msvc_traceability.domain.spi.RestaurantPersistencePort;
import com.pragma.challenge.msvc_traceability.domain.util.enums.OrderState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ReportUseCaseTest {

    @Mock
    private OrderLogPersistencePort orderLogPersistencePort;

    @Mock
    private RestaurantPersistencePort restaurantPersistencePort;

    @InjectMocks
    private ReportUseCase reportUseCase;

    private static final String RESTAURANT_ID = "restaurant-123";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(restaurantPersistencePort.getCurrentOwnerRestaurant()).thenReturn(Restaurant.builder()
                .setRestaurantId(RESTAURANT_ID)
                .build());
    }

    @Test
    void createOrdersReport_ShouldReturnCorrectCompletionTimes() {
        OrderLog log1 = createOrderLog(1L, "emp1", 10);
        OrderLog log2 = createOrderLog(2L, "emp2", 20);
        when(orderLogPersistencePort.findRestaurantOrders(RESTAURANT_ID)).thenReturn(List.of(log1, log2));

        List<OrderReport> reports = reportUseCase.createOrdersReport();

        assertEquals(2, reports.size());
        assertEquals(10, reports.get(0).getCompletionTime());
        assertEquals(20, reports.get(1).getCompletionTime());
    }

    @Test
    void createEmployeesReport_ShouldCalculateAverageCompletionTime() {
        OrderLog log1 = createOrderLog(1L, "emp1", 10);
        OrderLog log2 = createOrderLog(2L, "emp1", 20);
        OrderLog log3 = createOrderLog(3L, "emp2", 30);
        when(orderLogPersistencePort.findRestaurantOrders(RESTAURANT_ID)).thenReturn(List.of(log1, log2, log3));

        List<EmployeeReport> reports = reportUseCase.createEmployeesReport();

        assertEquals(2, reports.size());
        assertTrue(reports.stream().anyMatch(r -> r.getEmployeeId().equals("emp1") && r.getMediaDuration() == 15.0));
        assertTrue(reports.stream().anyMatch(r -> r.getEmployeeId().equals("emp2") && r.getMediaDuration() == 30.0));
    }

    private OrderLog createOrderLog(Long orderId, String employeeId, long durationMinutes) {
        OrderStateLog startState = new OrderStateLog(OrderState.WAITING, LocalDateTime.now().minusMinutes(durationMinutes));
        OrderStateLog endState = new OrderStateLog(OrderState.DELIVERED, LocalDateTime.now());
        return OrderLog.builder()
                .orderId(orderId)
                .assignedEmployeeId(employeeId)
                .states(List.of(startState, endState))
                .build();
    }
}