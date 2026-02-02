package com.pragma.challenge.msvc_traceability.domain.model;

public class Restaurant {
    private String ownerId;
    private String restaurantId;

    public Restaurant(RestaurantBuilder builder) {
        this.ownerId = builder.ownerId;
        this.restaurantId = builder.restaurantId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public static RestaurantBuilder builder(){
        return  new RestaurantBuilder();
    }

    public static class RestaurantBuilder{
        private String ownerId;
        private String restaurantId;

        public RestaurantBuilder setOwnerId(String ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        public RestaurantBuilder setRestaurantId(String restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public Restaurant build(){
            return new Restaurant(this);
        }
    }
}
