package com.cotato.team2.team2.service;

import com.cotato.team2.team2.domain.entity.User;
import com.cotato.team2.team2.service.component.UserCommonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserCommonService userCommonService;

    @Transactional
    public void updateNickname(String email, String nickname) {
        User user = userCommonService.findByEmail(email);
        user.updateNickname(nickname);
    }
}
