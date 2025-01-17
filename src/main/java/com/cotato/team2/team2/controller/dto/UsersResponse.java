package com.cotato.team2.team2.controller.dto;

import java.util.List;

public record UsersResponse(
        List<UserResponse> users
) {
}
