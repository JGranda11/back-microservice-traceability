package com.pragma.challenge.msvc_traceability.domain.api.security;

import com.pragma.challenge.msvc_traceability.domain.model.security.AuthorizedUser;

public interface AuthorizationServicePort {
    AuthorizedUser authorize(String token);
}
