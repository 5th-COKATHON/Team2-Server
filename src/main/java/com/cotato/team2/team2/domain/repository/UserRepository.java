package com.cotato.team2.team2.domain.repository;

import com.cotato.team2.team2.domain.entity.User;
import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findBySessionKey(String sessionKey);

    @Transactional(readOnly = true)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT u FROM User u WHERE u.sessionKey = :sessionKey")
    Optional<User> findBySessionKeyWithPessimisticXLock(String sessionKey);

    List<User> findAllByOrderByLevelDesc();
}
