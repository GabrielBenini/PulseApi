package com.gabrielbenini.pulseapi.model.dtos;

import com.gabrielbenini.pulseapi.model.entities.Comment;

import java.time.LocalDateTime;

public record CommentResponseDTO(
        Long id,
        String content,
        LocalDateTime createdAt,
        UserSummaryDTO author,
        Long postId

) {

    public static CommentResponseDTO fromEntity(Comment comment) {

        return new CommentResponseDTO(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                UserSummaryDTO.fromEntity(comment.getUser()),
                comment.getPost().getId()
        );
    }
}
