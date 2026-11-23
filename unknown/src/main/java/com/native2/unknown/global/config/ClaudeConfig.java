package com.native2.unknown.global.config;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Claude API 설정
 * API 키는 환경변수(ANTHROPIC_API_KEY)로 관리
 */
@Configuration
public class ClaudeConfig {

    @Value("${anthropic.api-key}")
    private String apiKey;

    /**
     * Anthropic API 클라이언트
     *
     * @return AnthropicClient 객체
     */
    @Bean
    public AnthropicClient anthropicClient() {
        return AnthropicOkHttpClient.builder()
            .apiKey(apiKey)
            .build();
    }
}
