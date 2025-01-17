package com.cotato.team2.team2.controller;

import com.cotato.team2.team2.controller.dto.ChangeCoinToPointRequest;
import com.cotato.team2.team2.controller.dto.UserLevelUpRequest;
import com.cotato.team2.team2.controller.dto.UserNickNameUpdateRequest;
import com.cotato.team2.team2.controller.dto.UserResponse;
import com.cotato.team2.team2.controller.dto.UsersResponse;
import com.cotato.team2.team2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getUser(@RequestParam("sessionKey") String sessionKey) {
        return ResponseEntity.ok(userService.getUser(sessionKey));
    }

    @PostMapping("/level-up")
    public ResponseEntity<UserResponse> levelUp(@RequestBody UserLevelUpRequest request) {
        return ResponseEntity.ok(userService.levelUp(request.sessionKey()));
    }

    @GetMapping
    public ResponseEntity<UsersResponse> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PatchMapping("/{id}/point")
    public ResponseEntity<UserResponse> usePoint(@PathVariable("id") Long id,
                                                @RequestParam("sessionKey") String sessionKey) {
        return ResponseEntity.ok(userService.usePoint(id, sessionKey));
    }

    @PostMapping("/coin")
    public ResponseEntity<UserResponse> changeCoinToPoint(@RequestBody ChangeCoinToPointRequest request) {
        return ResponseEntity.ok(userService.changeCoinToPoint(request.sessionKey(), request.coin()));
    }
}
