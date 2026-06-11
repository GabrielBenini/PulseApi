package com.gabrielbenini.pulseapi.model.dtos;

import com.gabrielbenini.pulseapi.model.entities.Comment;
import com.gabrielbenini.pulseapi.model.entities.Post;
import com.gabrielbenini.pulseapi.model.entities.User;
import jakarta.validation.constraints.NotBlank;

public record CommentRequestDTO(
        @NotBlank(message = "Content cant be blank")
        String content,
        Long postId,
        Long userId
) {

    public Comment toEntity(Post post, User user) {
        Comment comment = new Comment();
        comment.setContent(this.content);
        comment.setPost(post);
        comment.setUser(user);

        return comment;
    }
}
