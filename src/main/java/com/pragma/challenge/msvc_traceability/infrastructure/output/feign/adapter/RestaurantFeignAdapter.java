package com.pragma.challenge.msvc_traceability.infrastructure.output.feign.adapter;

import com.pragma.challenge.msvc_traceability.domain.model.Restaurant;
import com.pragma.challenge.msvc_traceability.domain.spi.RestaurantPersistencePort;
import com.pragma.challenge.msvc_traceability.infrastructure.output.feign.client.RestaurantFeign;
import com.pragma.challenge.msvc_traceability.infrastructure.output.feign.mapper.response.UserResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantFeignAdapter implements RestaurantPersistencePort {
    private final RestaurantFeign restaurantFeign;
    private final UserResponseMapper userResponseMapper;

    @Override
    public Restaurant getCurrentOwnerRestaurant() {
        return userResponseMapper.toDomain(
                restaurantFeign.getCurrentOwnerRestaurant()
        );
    }
}
