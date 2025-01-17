package com.cotato.team2.team2.service.component;

import com.cotato.team2.team2.domain.entity.DailyQuest;
import com.cotato.team2.team2.domain.entity.Quest;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestCommonService {

    private final DailyQuestCommonService dailyQuestCommonService;

    public List<Quest> getDailyQuests(final LocalDate date) {
        List<DailyQuest> dailyQuests = dailyQuestCommonService.findAllByDate(date);
        return dailyQuests.stream()
                .map(DailyQuest::getQuest)
                .toList();
    }
}
