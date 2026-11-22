package com.native2.unknown.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.Instant;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * JPA 엔티티 공통 필드
 * 생성 시각(createdAt)을 자동으로 관리하기 위한 기본 타임스탬프 필드
 * Instant 는 UTC 기준으로 저장된다 (hibernate.jdbc.time_zone: UTC)
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CreatedAtEntity {

    /**
     * 엔티티 생성 시간
     */
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

}
