package com.pragma.challenge.msvc_traceability.domain.spi;

import com.pragma.challenge.msvc_traceability.domain.model.Restaurant;

public interface RestaurantPersistencePort {
    Restaurant getCurrentOwnerRestaurant();
}
