package com.pragma.challenge.msvc_traceability.application.mapper.response;

import com.pragma.challenge.msvc_traceability.application.dto.response.ShortOrderLogResponse;
import com.pragma.challenge.msvc_traceability.domain.model.log.OrderLog;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderLogResponseMapper {
    ShortOrderLogResponse toResponse(OrderLog order);
}
