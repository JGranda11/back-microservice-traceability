package com.pragma.challenge.msvc_traceability.application.handler.impl;

import com.pragma.challenge.msvc_traceability.application.dto.response.EmployeeReportResponse;
import com.pragma.challenge.msvc_traceability.application.dto.response.OrderReportResponse;
import com.pragma.challenge.msvc_traceability.application.handler.ReportHandler;
import com.pragma.challenge.msvc_traceability.application.mapper.response.EmployeeReportResponseMapper;
import com.pragma.challenge.msvc_traceability.application.mapper.response.OrderReportResponseMapper;
import com.pragma.challenge.msvc_traceability.domain.api.ReportServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportHandlerImpl implements ReportHandler {
    private final ReportServicePort reportServicePort;
    private final EmployeeReportResponseMapper employeeReportResponseMapper;
    private final OrderReportResponseMapper orderReportResponseMapper;

    @Override
    public List<OrderReportResponse> getOrdersReport() {
        return orderReportResponseMapper.toResponses(
                reportServicePort.createOrdersReport()
        );
    }

    @Override
    public List<EmployeeReportResponse> getEmployeesReport() {
        return employeeReportResponseMapper.toResponses(
                reportServicePort.createEmployeesReport()
        );
    }
}
