package com.native2.unknown.domain.user.entity;

import com.native2.unknown.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * 사용자
 */
@Getter
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"login_type", "provider_id"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 성별
     */
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Gender gender;

    /**
     * 이름
     */
    @Column(length = 50)
    private String name;

    /**
     * 탈퇴 시각
     */
    @Column(name = "deleted_at")
    private Instant deletedAt;

    /**
     * 이메일
     */
    @Column(length = 255)
    private String email;

    /**
     * 로그인 타입 (소셜 로그인 제공자)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "login_type", nullable = false, length = 20)
    private LoginType loginType;

    /**
     * 소셜 로그인 제공자의 사용자 식별값
     */
    @Column(name = "provider_id")
    private String providerId;

    /**
     * 이용약관 동의 여부
     */
    @Column(name = "terms_agreed")
    private Boolean termsAgreed;

    @Builder
    private User(Gender gender, String name, String email, LoginType loginType, String providerId, Boolean termsAgreed) {
        this.gender = gender;
        this.name = name;
        this.email = email;
        this.loginType = loginType;
        this.providerId = providerId;
        this.termsAgreed = termsAgreed;
    }

    public void withdraw(Instant now) {
        this.providerId = null;
        this.email = null;
        this.name = null;
        this.gender = null;
        this.deletedAt = now;
    }
}
