package com.cotato.team2.team2.controller.dto;

public record ChangeCoinToPointRequest(
        String sessionKey,
        int coin
) {
}
