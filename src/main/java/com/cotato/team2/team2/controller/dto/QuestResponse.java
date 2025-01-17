package com.cotato.team2.team2.controller.dto;

import com.cotato.team2.team2.domain.entity.Quest;
import lombok.Builder;

@Builder
public record QuestResponse(
        Long id,
        String title,
        int point
) {
    public static QuestResponse from(Quest quest) {

        return QuestResponse.builder()
                .id(quest.getId())
                .title(quest.getTitle())
                .point(quest.getPoint())
                .build();
    }
}
