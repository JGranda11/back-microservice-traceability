package com.pragma.challenge.msvc_traceability.application.mapper.request;

import com.pragma.challenge.msvc_traceability.application.dto.request.NewOrderLogRequest;
import com.pragma.challenge.msvc_traceability.domain.model.OrderLog;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderLogRequestMapper {
    OrderLog toDomain(NewOrderLogRequest request);

}
