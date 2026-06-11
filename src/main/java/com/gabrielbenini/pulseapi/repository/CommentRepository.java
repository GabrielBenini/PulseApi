package com.gabrielbenini.pulseapi.repository;

import com.gabrielbenini.pulseapi.model.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
