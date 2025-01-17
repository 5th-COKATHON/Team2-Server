package com.cotato.team2.team2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AiMessage {
    private String role;
    private String content;
}
