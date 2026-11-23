package com.native2.unknown.domain.chat.controller;

import com.native2.unknown.domain.chat.dto.request.SendMessageRequest;
import com.native2.unknown.domain.chat.dto.response.SendMessageResponse;
import com.native2.unknown.domain.chat.service.SendMessageUseCase;
import com.native2.unknown.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 채팅 API
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

    private final SendMessageUseCase sendMessageUseCase;

    /**
     * 메시지를 보내고 AI 답변을 받음
     *
     * @param request 사용자 메시지
     * @return AI 답변
     */
    @PostMapping("/messages")
    public ApiResponse<SendMessageResponse> sendMessage(@Valid @RequestBody SendMessageRequest request) {
        return ApiResponse.success(sendMessageUseCase.execute(request));
    }
}
