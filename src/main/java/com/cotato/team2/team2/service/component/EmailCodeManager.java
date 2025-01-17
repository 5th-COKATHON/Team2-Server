package com.cotato.team2.team2.service.component;

import com.cotato.team2.team2.controller.dto.VerificationResponse;
import com.cotato.team2.team2.domain.redis.VerificationCodeRedisRepository;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailCodeManager {

    private static final int CODE_LENGTH = 6;
    private static final int CODE_BOUNDARY = 10;

    private final VerificationCodeRedisRepository verificationCodeRedisRepository;

    public String getVerificationCode(final String email) {
        String verificationCode = getRandomCode();
        verificationCodeRedisRepository.saveCodeWithEmail(email, verificationCode);
        return verificationCode;
    }


    private String getRandomCode() {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            builder.append(random.nextInt(CODE_BOUNDARY));
        }
        return String.valueOf(builder);
    }

    public boolean verifyCode(String email, String code) {
        String savedCode = verificationCodeRedisRepository.getByEmail(email);
        return savedCode.equals(code);
    }
}
