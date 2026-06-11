package com.gabrielbenini.pulseapi.service;

import com.gabrielbenini.pulseapi.exception.ResourceNotFoundException;
import com.gabrielbenini.pulseapi.model.dtos.LikeResponseDTO;
import com.gabrielbenini.pulseapi.model.entities.Like;
import com.gabrielbenini.pulseapi.model.entities.Post;
import com.gabrielbenini.pulseapi.model.entities.User;
import com.gabrielbenini.pulseapi.repository.LikeRepository;
import com.gabrielbenini.pulseapi.repository.PostRepository;
import com.gabrielbenini.pulseapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public LikeResponseDTO likePost(Long postId, Long userId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post nao encontrado com id: " + postId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado com id: " + userId));

        boolean alreadyLiked = likeRepository.existsByUserAndPost(user, post);

        if (!alreadyLiked){
            Like like = new Like();
            like.setUser(user);
            like.setPost(post);

            likeRepository.save(like);
        }

        long likesCount = likeRepository.countByPost(post);

        return new LikeResponseDTO(postId, true, likesCount);

    }

    @Transactional
    public LikeResponseDTO unlikePost(Long postId, Long userId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        likeRepository.deleteByUserAndPost(user, post);

        long likesCount = likeRepository.countByPost(post);

        return new LikeResponseDTO(postId, false, likesCount);
    }

}
