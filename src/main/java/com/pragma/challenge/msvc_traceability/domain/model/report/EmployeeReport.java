package com.pragma.challenge.msvc_traceability.domain.model.report;

public class EmployeeReport {
    private String employeeId;
    private Double mediaDuration;

    public EmployeeReport(EmployeeReportBuilder builder) {
        this.employeeId = builder.employeeId;
        this.mediaDuration = builder.mediaDuration;

    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Double getMediaDuration() {
        return mediaDuration;
    }

    public void setMediaDuration(Double mediaDuration) {
        this.mediaDuration = mediaDuration;
    }

    public static EmployeeReportBuilder builder(){
        return new EmployeeReportBuilder();
    }

    public static class EmployeeReportBuilder{
        private String employeeId;
        private Double mediaDuration;

        public EmployeeReportBuilder employeeId(String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public EmployeeReportBuilder mediaDuration(Double mediaDuration) {
            this.mediaDuration = mediaDuration;
            return this;
        }

        public EmployeeReport build(){
            return new EmployeeReport(this);
        }
    }
}
