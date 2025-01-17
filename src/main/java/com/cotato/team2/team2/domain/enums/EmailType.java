package com.cotato.team2.team2.domain.enums;

import com.cotato.team2.team2.domain.constant.EmailConstant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmailType {
    VERIFICATION(EmailConstant.CODE_SUBJECT),
    SEND_PASSWORD("안녕하세요:) 접근 키를 발송해드립니다.");

    private final String codeSubject;
}
