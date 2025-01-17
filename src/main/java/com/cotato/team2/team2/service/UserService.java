package com.cotato.team2.team2.service;

import com.cotato.team2.team2.controller.dto.UserResponse;
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

    public UserResponse getUser(String sessionKey) {
        User user = userCommonService.findBySessionKey(sessionKey);
        return UserResponse.from(user);
    }

    public UserResponse levelUp(String sessionKey) {
        User user = userCommonService.findBySessionKeyWithPessimisticXLock(sessionKey);
        user.levelUp();
        return UserResponse.from(user);
    }
}
