package com.cotato.team2.team2.service.component;

import static com.cotato.team2.team2.domain.constant.EmailConstant.MESSAGE_PREFIX;
import static com.cotato.team2.team2.domain.constant.EmailConstant.MESSAGE_SUFFIX;
import static com.cotato.team2.team2.domain.constant.EmailConstant.SENDER_EMAIL;
import static com.cotato.team2.team2.domain.constant.EmailConstant.SENDER_PERSONAL;

import com.cotato.team2.team2.domain.enums.EmailType;
import com.cotato.team2.team2.exception.BusinessException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;
import java.io.UnsupportedEncodingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailSender {

    private final JavaMailSender javaMailSender;

    public void sendEmail(String email, String key, EmailType type) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        mimeMessage.addRecipients(RecipientType.TO, email);
        mimeMessage.setText(getVerificationText(key), "utf-8", "html");
        mimeMessage.setSubject(type.getCodeSubject());
        mimeMessage.setFrom(getInternetAddress());
        javaMailSender.send(mimeMessage);

        log.info("이메일 전송 완료");
    }

    private String getVerificationText(String key) {
        StringBuilder sb = new StringBuilder();
        return String.valueOf(sb.append(MESSAGE_PREFIX)
                .append(key)
                .append(MESSAGE_SUFFIX));
    }

    private InternetAddress getInternetAddress() {
        try {
            return new InternetAddress(SENDER_EMAIL, SENDER_PERSONAL);
        } catch (UnsupportedEncodingException e) {
            throw new BusinessException(1001, "이메일 전송에 실패했습니다");
        }
    }
}
