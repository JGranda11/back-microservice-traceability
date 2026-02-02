package com.pragma.challenge.msvc_traceability.infrastructure.output.feign.mapper.response;

import com.pragma.challenge.msvc_traceability.domain.model.security.AuthorizedUser;
import com.pragma.challenge.msvc_traceability.infrastructure.output.feign.dto.response.AuthorizationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorizationResponseMapper {
    AuthorizedUser toDomain(AuthorizationResponse response);
    List<AuthorizedUser> toDomains(List<AuthorizationResponse> responses);
}
