package com.cotato.team2.team2.service.component;

import com.cotato.team2.team2.domain.entity.User;
import com.cotato.team2.team2.domain.repository.UserRepository;
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
}
