package com.cotato.team2.team2.service;

import com.cotato.team2.team2.exception.BusinessException;
import com.cotato.team2.team2.service.component.EmailCodeManager;
import com.cotato.team2.team2.service.component.EmailSender;
import com.cotato.team2.team2.service.component.UserReader;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserReader userReader;
    private final EmailSender emailSender;
    private final EmailCodeManager codeManager;

    public void sendEmail(final String email) throws MessagingException {
        if (userReader.existsByEmail(email)) {
            throw new BusinessException(2001, "이미 존재하는 유저입니다.");
        }

        final String code = codeManager.getVerificationCode(email);
        emailSender.sendEmail(email, code);
    }
}
