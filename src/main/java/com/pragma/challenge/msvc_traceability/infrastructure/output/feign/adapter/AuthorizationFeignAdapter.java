package com.pragma.challenge.msvc_traceability.infrastructure.output.feign.adapter;

import com.pragma.challenge.msvc_traceability.domain.model.security.AuthorizedUser;
import com.pragma.challenge.msvc_traceability.domain.spi.AuthorizationSecurityPort;
import com.pragma.challenge.msvc_traceability.infrastructure.output.feign.client.AuthFeign;
import com.pragma.challenge.msvc_traceability.infrastructure.output.feign.mapper.response.AuthorizationResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorizationFeignAdapter implements AuthorizationSecurityPort {
    private final AuthFeign authFeign;
    private final AuthorizationResponseMapper authorizationResponseMapper;

    @Override
    public AuthorizedUser authorize(String token) {
        return authorizationResponseMapper.toDomain(
                authFeign.authorize(token)
        );
    }
}