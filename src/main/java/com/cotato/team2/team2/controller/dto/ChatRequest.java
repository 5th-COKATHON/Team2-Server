package com.cotato.team2.team2.controller.dto;

public record ChatRequest(
        String sessionKey,
        String message
) {
}
