package com.pragma.challenge.msvc_traceability.application.mapper.response;

import com.pragma.challenge.msvc_traceability.application.dto.response.EmployeeReportResponse;
import com.pragma.challenge.msvc_traceability.domain.model.report.EmployeeReport;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeReportResponseMapper {
    EmployeeReportResponse toResponse(EmployeeReport order);
    List<EmployeeReportResponse> toResponses(List<EmployeeReport> orders);
}
