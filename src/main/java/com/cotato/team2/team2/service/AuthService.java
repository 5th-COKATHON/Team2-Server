package com.cotato.team2.team2.service;

import com.cotato.team2.team2.controller.dto.LoginRequest;
import com.cotato.team2.team2.controller.dto.LoginResponse;
import com.cotato.team2.team2.controller.dto.VerificationResponse;
import com.cotato.team2.team2.domain.entity.User;
import com.cotato.team2.team2.domain.enums.EmailType;
import com.cotato.team2.team2.exception.BusinessException;
import com.cotato.team2.team2.service.component.EmailCodeManager;
import com.cotato.team2.team2.service.component.EmailSender;
import com.cotato.team2.team2.service.component.UserCommonService;
import jakarta.mail.MessagingException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserCommonService userCommonService;
    private final EmailSender emailSender;
    private final EmailCodeManager codeManager;

    public void sendEmail(final String email) throws MessagingException {
        checkUserExist(email);

        final String code = codeManager.getVerificationCode(email);
        emailSender.sendEmail(email, code, EmailType.VERIFICATION);
    }

    private void checkUserExist(String email) {
        if (userCommonService.existsByEmail(email)) {
            throw new BusinessException(2001, "이미 존재하는 유저입니다.");
        }
    }

    public VerificationResponse verifyEmailAndCreateUser(String email, String code) throws MessagingException {
        checkUserExist(email);

        if (!codeManager.verifyCode(email, code)) {
            throw new BusinessException(2002, "인증 코드가 일치하지 않습니다.");
        }

        final UUID uuid = UUID.randomUUID();

        userCommonService.createUser(email, uuid);
        emailSender.sendEmail(email, uuid.toString(), EmailType.SEND_PASSWORD);

        return VerificationResponse.from(codeManager.verifyCode(email, code));
    }

    public LoginResponse login(String email, String authKey) {
        User user = userCommonService.findByEmail(email);
        if(!user.getAuthKey().equals(authKey)) {
            throw new BusinessException(2003, "인증 키가 일치하지 않습니다.");
        }

        UUID sessionKey = UUID.randomUUID();
        user.updateSessionKey(sessionKey.toString());

        return LoginRequest.from(user);
    }
}
