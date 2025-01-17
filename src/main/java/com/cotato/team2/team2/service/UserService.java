package com.cotato.team2.team2.service;

import com.cotato.team2.team2.controller.dto.UserResponse;
import com.cotato.team2.team2.controller.dto.UsersResponse;
import com.cotato.team2.team2.domain.entity.User;
import com.cotato.team2.team2.exception.BusinessException;
import com.cotato.team2.team2.service.component.UserCommonService;
import java.util.List;
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

    @Transactional
    public UserResponse levelUp(String sessionKey) {
        User user = userCommonService.findBySessionKeyWithPessimisticXLock(sessionKey);
        user.levelUp();
        return UserResponse.from(user);
    }

    public UsersResponse getUsers() {
        List<UserResponse> users = userCommonService.findAllOrderByLevelDesc().stream()
                .map(UserResponse::from)
                .toList();
        return new UsersResponse(users);
    }

    @Transactional
    public UserResponse usePoint(Long id, String sessionKey) {
        User user = userCommonService.findBySessionKeyWithPessimisticXLock(sessionKey);
        if (!user.getId().equals(id)) {
            throw new BusinessException(3000, "잘못된 접근입니다.");
        }
        user.usePoint();
        return UserResponse.from(user);
    }

    @Transactional
    public UserResponse changeCoinToPoint(Long id, String sessionKey, int coin) {
        User user = userCommonService.findBySessionKey(sessionKey);
        if (!user.getId().equals(id)) {
            throw new BusinessException(3000, "잘못된 접근입니다.");
        }
        if (user.getCoin() < coin) {
            throw new BusinessException(3001, "코인이 부족합니다.");
        }

        user.decreaseCoin(coin);
        user.increasePoint();
        return UserResponse.from(user);
    }
}
