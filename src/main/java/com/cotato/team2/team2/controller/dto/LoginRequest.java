package com.cotato.team2.team2.controller.dto;

import com.cotato.team2.team2.domain.entity.User;

public record LoginRequest(
        String email,
        String authKey
) {
    public static LoginResponse from(User user) {
        return new LoginResponse(user.getId(), user.getSessionKey());
    }
}
