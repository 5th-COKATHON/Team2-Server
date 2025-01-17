package com.cotato.team2.team2.controller.dto;

public record VerificationResponse(
        boolean success
) {
    public static VerificationResponse from(boolean success) {
        return new VerificationResponse(success);
    }
}
