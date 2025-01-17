package com.cotato.team2.team2.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nickname;

    private String authKey;

    private String sessionKey;

    private int level;

    @Builder
    public User(String email, String nickname, String authKey, String sessionKey, int level) {
        this.email = email;
        this.nickname = nickname;
        this.authKey = authKey;
        this.sessionKey = sessionKey;
        this.level = level;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public void levelUp() {
        this.level += 1;
    }
}
