package com.gabrielbenini.pulseapi.model.dtos;

public record FollowResponseDTO(
        Long userId,
        boolean following,
        Long followersCount
) {
}
