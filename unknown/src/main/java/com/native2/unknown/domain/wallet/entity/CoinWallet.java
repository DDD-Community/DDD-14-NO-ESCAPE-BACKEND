package com.native2.unknown.domain.wallet.entity;

import com.native2.unknown.domain.user.entity.User;
import com.native2.unknown.global.exception.BusinessException;
import com.native2.unknown.global.exception.ErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 코인 지갑
 * 사용자와 1:1 관계이며, 사용자 ID 를 PK 로 공유한다
 */
@Getter
@Entity
@Table(name = "coin_wallets")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoinWallet {

    @Id
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    /**
     * 잔액
     */
    @Column(nullable = false)
    private int balance;

    /**
     * 마지막 수정 시간
     */
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public CoinWallet(User user) {
        this.user = user;
        this.balance = 0;
    }

    public void charge(int amount) {
        this.balance += amount;
    }

    public void use(int amount) {
        if (this.balance < amount) {
            throw new BusinessException(ErrorCode.INSUFFICIENT_COIN);
        }
        this.balance -= amount;
    }
}
