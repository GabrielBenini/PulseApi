package com.gabrielbenini.pulseapi.model.dtos;

import com.gabrielbenini.pulseapi.model.entities.User;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank
        String email,
        @NotBlank
        String password
) {

    public User toEntity(){

        User user = new User();
        user.setEmail(this.email);
        user.setPassword(this.password);

        return user;
    }
}
