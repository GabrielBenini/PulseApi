package com.gabrielbenini.pulseapi.repository;

import com.gabrielbenini.pulseapi.model.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostIdOrderByCreatedAtDesc(Long postId);

}
