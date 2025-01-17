package com.cotato.team2.team2.service;

import com.cotato.team2.team2.controller.dto.QuestResponse;
import com.cotato.team2.team2.controller.dto.QuestsResponse;
import com.cotato.team2.team2.service.component.QuestCommonService;
import com.cotato.team2.team2.util.TimeUtil;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestService {

    private final QuestCommonService questCommonService;

    public QuestsResponse getQuests() {
        LocalDate date = TimeUtil.getToday();
        List<QuestResponse> responses = questCommonService.getDailyQuests(date).stream()
                .map(QuestResponse::from)
                .toList();
        return QuestsResponse.of(date, responses);
    }
}
