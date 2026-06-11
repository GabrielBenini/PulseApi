package com.gabrielbenini.pulseapi.model.dtos;

import com.gabrielbenini.pulseapi.model.entities.Post;

import java.time.LocalDateTime;

public record PostResponseDTO(
        Long id,
        String content,
        String imageUrl,
        LocalDateTime createdAt,
        Long userId,
        Long likesCount,
        Long commentsCount,
        Boolean likedByUser,
        UserSummaryDTO author
) {

    public static PostResponseDTO fromEntity(Post post, Long currentUserId) {

        Long likesCount = post.getLikes() != null ? (long) post.getLikes().size() : 0L;
        Long commentsCount = post.getComments() != null ? (long) post.getComments().size() : 0L;

        boolean likedByUser = false;

        if (post.getLikes() != null) {
            likedByUser = post.getLikes()
                    .stream()
                    .anyMatch(like -> like.getUser().getId().equals(currentUserId));
        }

        return new PostResponseDTO(
                post.getId(),
                post.getContent(),
                post.getImageUrl(),
                post.getCreatedAt(),
                post.getUser().getId(),
                likesCount,
                commentsCount,
                likedByUser,
                UserSummaryDTO.fromEntity(post.getUser())
        );
    }
}