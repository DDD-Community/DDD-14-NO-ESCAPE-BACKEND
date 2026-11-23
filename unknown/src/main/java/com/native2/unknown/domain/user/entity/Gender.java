package com.native2.unknown.domain.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 성별
 * label 은 프롬프트의 주인공 성별 항목에 그대로 들어가는 값
 */
@Getter
@RequiredArgsConstructor
public enum Gender {
    MALE("남자"),
    FEMALE("여자"),
    UNDISCLOSED("밝히지 않음");

    private final String label;
}
