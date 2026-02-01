package com.pragma.challenge.msvc_traceability.infrastructure.configuration;

import com.pragma.challenge.msvc_traceability.domain.api.OrderLogServicePort;
import com.pragma.challenge.msvc_traceability.domain.api.security.AuthorizationServicePort;
import com.pragma.challenge.msvc_traceability.domain.spi.AuthorizationSecurityPort;
import com.pragma.challenge.msvc_traceability.domain.spi.OrderLogPersistencePort;
import com.pragma.challenge.msvc_traceability.domain.usecase.AuthorizationUseCase;
import com.pragma.challenge.msvc_traceability.domain.usecase.OrderLogUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class BeanConfiguration {
    @Bean
    public OrderLogServicePort orderLogServicePort(
            OrderLogPersistencePort orderLogPersistencePort
    ){
        return new OrderLogUseCase(orderLogPersistencePort);
    }

    @Bean
    public AuthorizationServicePort authorizationServicePort(
            AuthorizationSecurityPort authorizationSecurityPort
    ) {
        return new AuthorizationUseCase(authorizationSecurityPort);
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
}
