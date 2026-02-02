package com.pragma.challenge.msvc_traceability.domain.model.log;

import com.pragma.challenge.msvc_traceability.domain.util.enums.OrderState;

import java.time.LocalDateTime;

public class OrderStateLog {
    private OrderState state;
    private LocalDateTime timestamp;

    public OrderStateLog(OrderState state, LocalDateTime timestamp) {
        this.state = state;
        this.timestamp = timestamp;
    }

    public OrderStateLog(OrderStateLogBuilder builder) {
        this.state = builder.state;
        this.timestamp = builder.timestamp;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public static OrderStateLogBuilder builder(){
        return new OrderStateLogBuilder();
    }

    public static class OrderStateLogBuilder{
        private OrderState state;
        private LocalDateTime timestamp;

        public OrderStateLogBuilder state(OrderState state) {
            this.state = state;
            return this;
        }

        public OrderStateLogBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public OrderStateLog build(){
            return new OrderStateLog(this);
        }
    }
}
