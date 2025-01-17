package com.cotato.team2.team2.controller;

import com.cotato.team2.team2.controller.dto.ChatRequest;
import com.cotato.team2.team2.controller.dto.ChatResponse;
import com.cotato.team2.team2.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    @GetMapping
    public ResponseEntity<ChatResponse> chat(@RequestParam("sessionKey") String sessionKey,
                                             @RequestParam("message") String message) {
        return ResponseEntity.ok(chatService.chat(sessionKey, message));
    }
}
