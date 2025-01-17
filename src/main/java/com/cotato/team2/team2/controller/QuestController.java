package com.cotato.team2.team2.controller;

import com.cotato.team2.team2.controller.dto.QuestsResponse;
import com.cotato.team2.team2.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quests")
public class QuestController {

    private final QuestService questService;

    @GetMapping("/daily")
    public ResponseEntity<QuestsResponse> getQuests() {
        return ResponseEntity.ok(questService.getQuests());
    }
}
