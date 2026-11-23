package com.native2.unknown.domain.chat.service;

import com.native2.unknown.domain.chat.dto.request.SendMessageRequest;
import com.native2.unknown.domain.chat.dto.response.SendMessageResponse;
import com.native2.unknown.external.claude.ClaudeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 채팅 메시지 전송
 */
@Service
@RequiredArgsConstructor
public class SendMessageUseCase {

    private final ClaudeClient claudeClient;

    /**
     * 사용자 메시지를 Claude 에 보내고 답변을 반환
     *
     * @param request 사용자 메시지
     * @return AI 답변
     */
    public SendMessageResponse execute(SendMessageRequest request) {

        String answer = claudeClient.ask(request.getMessage());

        return SendMessageResponse.from(answer);
    }
}
