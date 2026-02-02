package com.pragma.challenge.msvc_traceability.domain.api;

import com.pragma.challenge.msvc_traceability.domain.model.report.EmployeeReport;
import com.pragma.challenge.msvc_traceability.domain.model.report.OrderReport;

import java.util.List;

public interface ReportServicePort {
    List<OrderReport> createOrdersReport();
    List<EmployeeReport> createEmployeesReport();
}
