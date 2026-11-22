package com.native2.unknown.domain.chat.repository;

import com.native2.unknown.domain.chat.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
