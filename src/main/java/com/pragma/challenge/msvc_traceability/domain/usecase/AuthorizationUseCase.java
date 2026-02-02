package com.pragma.challenge.msvc_traceability.domain.usecase;

import com.pragma.challenge.msvc_traceability.domain.api.security.AuthorizationServicePort;
import com.pragma.challenge.msvc_traceability.domain.exception.NotAuthorizedException;
import com.pragma.challenge.msvc_traceability.domain.model.security.AuthorizedUser;
import com.pragma.challenge.msvc_traceability.domain.spi.AuthorizationSecurityPort;

public class AuthorizationUseCase implements AuthorizationServicePort {

    private final AuthorizationSecurityPort authorizationSecurityPort;

    public AuthorizationUseCase(AuthorizationSecurityPort authorizationSecurityPort) {
        this.authorizationSecurityPort = authorizationSecurityPort;
    }

    @Override
    public AuthorizedUser authorize(String token) {
        try{
            return authorizationSecurityPort.authorize(token);
        } catch (Exception e){
            throw new NotAuthorizedException();
        }
    }
}
