package com.cotato.team2.team2.domain.entity;

import com.cotato.team2.team2.exception.BusinessException;
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

    private int userPoint;

    private int level;

    private int coin;

    @Builder
    public User(String email, String nickname, String authKey, String sessionKey, int level) {
        this.email = email;
        this.nickname = nickname;
        this.authKey = authKey;
        this.sessionKey = sessionKey;
        this.level = level;
        this.userPoint = 3;
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

    public void usePoint() {
        if (userPoint <= 0) {
            throw new BusinessException(3003, "포인트가 부족합니다.");
        }
        this.userPoint -= 1;
    }

    public void decreaseCoin(int coin) {
        if (this.coin < coin) {
            throw new BusinessException(3004, "코인이 부족합니다.");
        }
        this.coin -= coin;
    }

    public void increasePoint() {
        if (this.userPoint >= 3) {
            throw new BusinessException(3005, "포인트는 3까지만 보유할 수 있습니다.");
        }
    }
}
