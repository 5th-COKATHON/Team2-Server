package com.cotato.team2.team2.domain.repository;

import com.cotato.team2.team2.domain.entity.DailyQuest;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyQuestRepository extends JpaRepository<DailyQuest, Long> {
    List<DailyQuest> findAllByDate(LocalDate localDate);
}
