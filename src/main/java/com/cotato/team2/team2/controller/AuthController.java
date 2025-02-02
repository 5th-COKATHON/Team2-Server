package com.cotato.team2.team2.controller;

import com.cotato.team2.team2.controller.dto.SendEmailRequest;
import com.cotato.team2.team2.controller.dto.VerificationResponse;
import com.cotato.team2.team2.service.AuthService;
import jakarta.mail.MessagingException;
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
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/join/email")
    public ResponseEntity<Void> createUser(@RequestBody SendEmailRequest request) throws MessagingException {
        authService.sendEmail(request.email());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/verify/email")
    public ResponseEntity<VerificationResponse> verifyEmail(@RequestParam("email") String email, @RequestParam("code") String code)
            throws MessagingException {
        return ResponseEntity.ok(authService.verifyEmailAndCreateUser(email, code));
    }
}
