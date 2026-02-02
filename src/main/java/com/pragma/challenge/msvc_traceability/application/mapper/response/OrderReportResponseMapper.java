package com.pragma.challenge.msvc_traceability.application.mapper.response;

import com.pragma.challenge.msvc_traceability.application.dto.response.OrderReportResponse;
import com.pragma.challenge.msvc_traceability.domain.model.report.OrderReport;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderReportResponseMapper {
    OrderReportResponse toResponse(OrderReport order);
    List<OrderReportResponse> toResponses(List<OrderReport> orders);
}
