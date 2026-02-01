package com.pragma.challenge.msvc_traceability.domain.model;

import java.util.List;

public class OrderLog {
    private String id;
    private Long orderId;
    private String customerId;
    private String restaurantId;
    private String assignedEmployeeId;
    private List<OrderStateLog> states;

    private OrderLog(OrderLogBuilder builder) {
        this.id = builder.id;
        this.orderId = builder.orderId;
        this.customerId = builder.customerId;
        this.restaurantId = builder.restaurantId;
        this.assignedEmployeeId = builder.assignedEmployeeId;
        this.states = builder.states;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getAssignedEmployeeId() {
        return assignedEmployeeId;
    }

    public void setAssignedEmployeeId(String assignedEmployeeId) {
        this.assignedEmployeeId = assignedEmployeeId;
    }

    public List<OrderStateLog> getStates() {
        return states;
    }

    public void setStates(List<OrderStateLog> states) {
        this.states = states;
    }

    public static OrderLogBuilder builder(){
        return new OrderLogBuilder();
    }

    public static class OrderLogBuilder{
        private String id;
        private Long orderId;
        private String customerId;
        private String restaurantId;
        private String assignedEmployeeId;
        private List<OrderStateLog> states;

        public OrderLogBuilder id(String id) {
            this.id = id;
            return this;
        }

        public OrderLogBuilder orderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public OrderLogBuilder customerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public OrderLogBuilder restaurantId(String restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public OrderLogBuilder assignedEmployeeId(String assignedEmployeeId) {
            this.assignedEmployeeId = assignedEmployeeId;
            return this;
        }

        public OrderLogBuilder states(List<OrderStateLog> states) {
            this.states = states;
            return this;
        }

        public OrderLog build(){
            return new OrderLog(this);
        }
    }
}
