package com.cotato.team2.team2.exception;

public record CustomErrorResponse(
        int code,
        String message,
        String method,
        String requestURI
) {
    public static CustomErrorResponse of(int code, String message, String method, String requestURI) {
        return new CustomErrorResponse(code, message, method, requestURI);
    }
}
