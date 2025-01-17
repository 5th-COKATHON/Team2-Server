package com.cotato.team2.team2.domain.repository;

import com.cotato.team2.team2.domain.entity.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<Quest, Long> {
}
