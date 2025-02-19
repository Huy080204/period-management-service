package com.example.periodmanagement.enumeration;

public enum ErrorCode {
    INVALID_FORM("ERROR", "Invalid form"),
    UNAUTHENTICATED("ERROR", "Unauthenticated"),
    PERIOD_EXITED("ERROR", "Period already exists"),
    PERIOD_NOT_FOUND("NOT_FOUND", "Period not found"),
    LECTURER_SCHEDULER_NOT_FOUND("NOT_FOUND", "Lecturer scheduler not found"),
    LECTURER_SCHEDULER_EXITED("ERROR", "Lecturer scheduler already exists"),
    ;

    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
