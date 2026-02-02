package com.pragma.challenge.msvc_traceability.application.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeReportResponse {
    private String employeeId;
    private Double mediaDuration;
}
