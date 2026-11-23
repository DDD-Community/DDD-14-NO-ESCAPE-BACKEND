package com.native2.unknown.domain.chat.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

/**
 * 채팅 메시지 전송 응답 DTO
 */
@Getter
@Builder(access = AccessLevel.PRIVATE)
public class SendMessageResponse {

    @Schema(description = "AI 답변", example = "안녕하세요! 저는 Anthropic 이 만든 AI 어시스턴트 Claude 입니다.")
    private final String answer;

    public static SendMessageResponse from(String answer) {
        return SendMessageResponse.builder()
            .answer(answer)
            .build();
    }
}
