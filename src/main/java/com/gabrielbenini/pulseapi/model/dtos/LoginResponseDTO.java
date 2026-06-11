package com.gabrielbenini.pulseapi.model.dtos;

import com.gabrielbenini.pulseapi.model.entities.User;

public record LoginResponseDTO(
        Long id,
        String email
) {

    public LoginResponseDTO fromEntity(User user){

        return new LoginResponseDTO(
                user.getId(),
                user.getEmail()
        );
    }
}
