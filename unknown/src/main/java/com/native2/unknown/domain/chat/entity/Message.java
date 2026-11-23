package com.native2.unknown.domain.chat.entity;

import com.native2.unknown.global.entity.CreatedAtEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 채팅 메시지
 */
@Getter
@Entity
@Table(name = "messages")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends CreatedAtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoom chatRoom;

    /**
     * 역할 (USER / ASSISTANT)
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 12)
    private MessageRole role;

    /**
     * 내용
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Builder
    private Message(ChatRoom chatRoom, MessageRole role, String content) {
        this.chatRoom = chatRoom;
        this.role = role;
        this.content = content;
    }
}
