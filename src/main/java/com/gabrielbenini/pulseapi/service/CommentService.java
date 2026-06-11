package com.gabrielbenini.pulseapi.service;

import com.gabrielbenini.pulseapi.exception.ResourceNotFoundException;
import com.gabrielbenini.pulseapi.model.dtos.CommentRequestDTO;
import com.gabrielbenini.pulseapi.model.dtos.CommentResponseDTO;
import com.gabrielbenini.pulseapi.model.entities.Comment;
import com.gabrielbenini.pulseapi.model.entities.Post;
import com.gabrielbenini.pulseapi.model.entities.User;
import com.gabrielbenini.pulseapi.repository.CommentRepository;
import com.gabrielbenini.pulseapi.repository.PostRepository;
import com.gabrielbenini.pulseapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentResponseDTO createComment(CommentRequestDTO requestDTO){

        Post post = postRepository.findById(requestDTO.postId())
                .orElseThrow(() -> new ResourceNotFoundException("Post nao encontrado"));

        User user = userRepository.findById(requestDTO.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User nao encontrado"));

        Comment comment = requestDTO.toEntity(post, user);
        commentRepository.save(comment);

        return CommentResponseDTO.fromEntity(comment);

    }
}
