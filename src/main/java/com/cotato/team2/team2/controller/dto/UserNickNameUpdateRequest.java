package com.cotato.team2.team2.controller.dto;

import jakarta.validation.constraints.NotNull;

public record UserNickNameUpdateRequest(
        @NotNull
        String email,
        @NotNull
        String nickname
) {
}
