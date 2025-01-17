package com.cotato.team2.team2.controller;

import com.cotato.team2.team2.controller.dto.UserNickNameUpdateRequest;
import com.cotato.team2.team2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PatchMapping("/nickname")
    public ResponseEntity<Void> updateNickname(@RequestBody UserNickNameUpdateRequest request) {
        userService.updateNickname(request.email(), request.nickname());
        return ResponseEntity.ok().build();
    }
}
