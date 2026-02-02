package com.pragma.challenge.msvc_traceability.domain.model.report;

public class OrderReport {
    private Long orderId;
    private Long completionTime;

    public OrderReport(OrderReportBuilder builder) {
        this.orderId = builder.orderId;
        this.completionTime = builder.completionTime;
    }

    public Long getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Long completionTime) {
        this.completionTime = completionTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public static OrderReportBuilder builder(){
        return new OrderReportBuilder();
    }

    public static class OrderReportBuilder {
        private Long orderId;
        private Long completionTime;

        public OrderReportBuilder orderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public OrderReportBuilder completionTime(Long duration) {
            this.completionTime = duration;
            return this;
        }

        public OrderReport build(){
            return new OrderReport(this);
        }
    }
}
