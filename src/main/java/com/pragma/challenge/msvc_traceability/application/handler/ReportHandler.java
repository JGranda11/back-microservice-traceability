package com.pragma.challenge.msvc_traceability.application.handler;

import com.pragma.challenge.msvc_traceability.application.dto.response.EmployeeReportResponse;
import com.pragma.challenge.msvc_traceability.application.dto.response.OrderReportResponse;

import java.util.List;

public interface ReportHandler {
    List<OrderReportResponse> getOrdersReport();
    List<EmployeeReportResponse> getEmployeesReport();
}
