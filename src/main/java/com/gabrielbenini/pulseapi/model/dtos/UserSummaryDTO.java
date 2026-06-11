package com.gabrielbenini.pulseapi.model.dtos;

import com.gabrielbenini.pulseapi.model.entities.User;

public record UserSummaryDTO(
        Long id,
        String username,
        String imageUrl
) {

    public static UserSummaryDTO fromEntity(User user) {
        return new UserSummaryDTO(
                user.getId(),
                user.getUsername(),
                user.getImageUrl()
        );
    }
}
