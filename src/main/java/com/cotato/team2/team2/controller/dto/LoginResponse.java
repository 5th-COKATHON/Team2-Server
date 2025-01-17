package com.cotato.team2.team2.controller.dto;

public record LoginResponse(
        Long userId,
        String sessionKey
) {
}
