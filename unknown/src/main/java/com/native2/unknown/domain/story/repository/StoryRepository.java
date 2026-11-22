package com.native2.unknown.domain.story.repository;

import com.native2.unknown.domain.story.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long> {
}
