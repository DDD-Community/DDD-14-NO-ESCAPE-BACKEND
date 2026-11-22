package com.native2.unknown.domain.story.entity;

import com.native2.unknown.global.entity.CreatedAtEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * 스토리 (작품)
 */
@Getter
@Entity
@Table(name = "stories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Story extends CreatedAtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 타이틀
     */
    @Column(nullable = false, length = 100)
    private String title;

    /**
     * 작품 한 줄 소개
     */
    @Column(length = 255)
    private String summary;

    /**
     * 썸네일 이미지
     */
    @Column(name = "cover_image_url", columnDefinition = "TEXT")
    private String coverImageUrl;

    /**
     * 처음 선택지
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "initial_choices")
    private List<String> initialChoices;

    /**
     * 프롬프트 설정
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "prompt_config")
    private Map<String, Object> promptConfig;

    /**
     * 출시 여부
     */
    @Column(name = "is_published", nullable = false)
    private boolean published;

    @Builder
    private Story(String title, String summary, String coverImageUrl, List<String> initialChoices,
                  Map<String, Object> promptConfig, boolean published) {
        this.title = title;
        this.summary = summary;
        this.coverImageUrl = coverImageUrl;
        this.initialChoices = initialChoices;
        this.promptConfig = promptConfig;
        this.published = published;
    }
}
