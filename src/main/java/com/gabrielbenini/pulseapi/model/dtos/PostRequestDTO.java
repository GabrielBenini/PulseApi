package com.gabrielbenini.pulseapi.model.dtos;

import com.gabrielbenini.pulseapi.model.entities.Post;
import com.gabrielbenini.pulseapi.model.entities.User;
import jakarta.validation.constraints.NotBlank;

public record PostRequestDTO(
        @NotBlank(message = "Content cant be blank")
        String content,
        String imageUrl,
        Long userId
) {
    public Post toEntity(User user){

        Post post = new Post();
        post.setContent(this.content);
        post.setImageUrl(this.imageUrl);
        post.setUser(user);

        return post;
    }
}
