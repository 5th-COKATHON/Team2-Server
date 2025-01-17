package com.cotato.team2.team2.service.component;

import com.cotato.team2.team2.domain.entity.User;
import com.cotato.team2.team2.domain.repository.UserRepository;
import com.cotato.team2.team2.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserCommonService {

    private final UserRepository userRepository;

    public boolean existsByEmail(final String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public void createUser(String email, UUID uuid) {
        User user = User.builder()
                .email(email)
                .authKey(uuid.toString())
                .build();

        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("해당 이메일로 가입된 유저가 없습니다."));
    }

    public User findBySessionKey(String sessionKey) {
        return userRepository.findBySessionKey(sessionKey)
                .orElseThrow(() -> new EntityNotFoundException("해당 키의 유저가 존재하지 않음"));
    }

    public User findBySessionKeyWithPessimisticXLock(String sessionKey) {
        return userRepository.findBySessionKeyWithPessimisticXLock(sessionKey)
                .orElseThrow(() -> new EntityNotFoundException("해당 키의 유저가 존재하지 않음"));
    }
}
