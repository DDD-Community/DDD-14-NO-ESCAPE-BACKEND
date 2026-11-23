package com.native2.unknown.external.claude;

import com.anthropic.client.AnthropicClient;
import com.anthropic.errors.AnthropicException;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.OutputConfig;
import com.anthropic.models.messages.StopReason;
import com.anthropic.models.messages.TextBlock;
import com.native2.unknown.global.exception.BusinessException;
import com.native2.unknown.global.exception.ErrorCode;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Claude API 호출 클라이언트
 * 안전 필터에 의해 거절되면 다른 모델로 넘기지 않고 AI_REFUSED 로 처리
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ClaudeClient {

    private static final long MAX_TOKENS = 16000L;

    private final AnthropicClient anthropicClient;

    @Value("${anthropic.model}")
    private String model;

    /**
     * 질문을 보내고 Claude 의 답변 텍스트를 반환
     *
     * @param message 사용자 질문
     * @return Claude 답변
     */
    public String ask(String message) {
        MessageCreateParams params = MessageCreateParams.builder()
            .model(model)
            .maxTokens(MAX_TOKENS)
            .outputConfig(OutputConfig.builder()
                .effort(OutputConfig.Effort.MEDIUM)
                .build())
            .addUserMessage(message)
            .build();

        Message response;
        try {
            response = anthropicClient.messages().create(params);
        } catch (AnthropicException e) {
            log.error("Claude API call failed", e);
            throw new BusinessException(ErrorCode.AI_API_ERROR);
        }

        if (response.stopReason().filter(StopReason.REFUSAL::equals).isPresent()) {
            response.stopDetails().ifPresentOrElse(
                details -> log.warn("Claude refused the request: category={}, explanation={}",
                    details.category(), details.explanation()),
                () -> log.warn("Claude refused the request"));
            throw new BusinessException(ErrorCode.AI_REFUSED);
        }

        return response.content().stream()
            .flatMap(block -> block.text().stream())
            .map(TextBlock::text)
            .collect(Collectors.joining());
    }
}
