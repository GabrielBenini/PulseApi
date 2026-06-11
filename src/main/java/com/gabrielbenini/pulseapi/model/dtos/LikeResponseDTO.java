package com.gabrielbenini.pulseapi.model.dtos;

public record LikeResponseDTO(
        Long postId,
        boolean liked,
        Long likesCount
) {
}
