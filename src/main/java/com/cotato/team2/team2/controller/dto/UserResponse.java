package com.cotato.team2.team2.controller.dto;

import com.cotato.team2.team2.domain.entity.User;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record UserResponse(
        Long userId,
        String email,
        String nickname,
        int level
) {
    public static UserResponse from(User user) {
        return UserResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .level(user.getLevel())
                .build();
    }
}
