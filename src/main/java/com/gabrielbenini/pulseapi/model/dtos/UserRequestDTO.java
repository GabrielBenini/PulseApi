package com.gabrielbenini.pulseapi.model.dtos;

import com.gabrielbenini.pulseapi.model.entities.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Valid
public record UserRequestDTO(

        @NotBlank(message = "Username cannot be blank")
        String username,
        @Email(message = "Email should be valid")
        String email,
        @NotBlank(message = "Password cannot be blank")
        String password,
        String imageUrl
) {

    public User toEntity(){

        User user = new User();
        user.setUsername(this.username);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setImageUrl(this.imageUrl);

        return user;
    }
}

