package com.gabrielbenini.pulseapi.model.dtos;

import com.gabrielbenini.pulseapi.model.entities.User;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String username,
        String email,
        String imageUrl,
        LocalDateTime createdAt,
        Long followersCount,
        Long followingCount
) {

    public static UserResponseDTO fromEntity(User user) {

        Long followers = user.getFollowers() != null ? (long) user.getFollowers().size(): 0L;
        Long following = user.getFollowing() != null ? (long) user.getFollowers().size(): 0L;

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getImageUrl(),
                user.getCreatedAt(),
                followers,
                following
        );
    }
}
