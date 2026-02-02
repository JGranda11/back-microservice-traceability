package com.pragma.challenge.msvc_traceability.infrastructure.output.feign.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthorizationRequest {
    private String token;
}
