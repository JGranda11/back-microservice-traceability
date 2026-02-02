package com.pragma.challenge.msvc_traceability.infrastructure.output.feign.client;

import com.pragma.challenge.msvc_traceability.infrastructure.configuration.feign.FeignClientConfiguration;
import com.pragma.challenge.msvc_traceability.infrastructure.output.feign.dto.response.OwnerRestaurantResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "RESTAURANT-CLIENT",
        url = "http://localhost:8082/v1/restaurants",
        configuration = FeignClientConfiguration.class
)
public interface RestaurantFeign {
    @GetMapping(value = "/owner", consumes = MediaType.APPLICATION_JSON_VALUE)
    OwnerRestaurantResponse getCurrentOwnerRestaurant();
}
