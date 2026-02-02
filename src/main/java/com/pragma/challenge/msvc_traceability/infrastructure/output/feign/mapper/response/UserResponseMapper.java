package com.pragma.challenge.msvc_traceability.infrastructure.output.feign.mapper.response;

import com.pragma.challenge.msvc_traceability.domain.model.Restaurant;
import com.pragma.challenge.msvc_traceability.infrastructure.output.feign.dto.response.OwnerRestaurantResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserResponseMapper {
    Restaurant toDomain(OwnerRestaurantResponse ownerRestaurantResponse);
}
