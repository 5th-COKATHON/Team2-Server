package com.cotato.team2.team2.service.component;

import com.cotato.team2.team2.domain.entity.DailyQuest;
import com.cotato.team2.team2.domain.repository.DailyQuestRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DailyQuestCommonService {

    private final DailyQuestRepository dailyQuestRepository;

    public List<DailyQuest> findAllByDate(final LocalDate localDate) {
        return dailyQuestRepository.findAllByDate(localDate);
    }
}
