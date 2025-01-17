package com.cotato.team2.team2.controller.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;

@Builder
public record QuestsResponse(
        LocalDate date,
        List<QuestResponse> quests
) {
    public static QuestsResponse of(LocalDate date, List<QuestResponse> quests) {
        return new QuestsResponse(date, quests);
    }
}
