package com.native2.unknown.domain.chat.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * 채팅 메시지 전송 요청 DTO
 */
@Getter
public class SendMessageRequest {

    @NotBlank(message = "메시지를 입력해주세요.")
    @Schema(
        description = "사용자 메시지",
        example = "안녕? 한 문장으로 자기소개 해줘",
        requiredMode = RequiredMode.REQUIRED
    )
    private String message;
}
