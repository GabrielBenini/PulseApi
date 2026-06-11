package com.gabrielbenini.pulseapi.service;

import com.gabrielbenini.pulseapi.exception.ResourceNotFoundException;
import com.gabrielbenini.pulseapi.model.dtos.PostRequestDTO;
import com.gabrielbenini.pulseapi.model.dtos.PostResponseDTO;
import com.gabrielbenini.pulseapi.model.entities.Post;
import com.gabrielbenini.pulseapi.model.entities.User;
import com.gabrielbenini.pulseapi.repository.PostRepository;
import com.gabrielbenini.pulseapi.repository.UserRepository;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostResponseDTO createPost(PostRequestDTO requestDTO){

        User user = userRepository.findById(requestDTO.userId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado"));

        Post post = requestDTO.toEntity(user);
        postRepository.save(post);

        sendFeedEvent(post.getId());

        return PostResponseDTO.fromEntity(post, user.getId());
    }

    public void sendFeedEvent(Long postId){

        Firestore db = FirestoreClient.getFirestore();

        Map<String, Object> event = new HashMap<>();
        event.put("type", "NEW_POST");
        event.put("postId", postId);
        event.put("timestamp", System.currentTimeMillis());

        db.collection("feed_events").add(event);
    }

    public List<PostResponseDTO> getPosts(Long currentUserId){

        return postRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(post -> PostResponseDTO.fromEntity(post, currentUserId))
                .toList();
    }

    public void deletePostById(Long postId, Long userId){

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post nao encontrado com o ID" + postId));

        if (!post.getUser().getId().equals(userId)) {
            throw new RuntimeException("Voce nao pode deletar esse post");
        }

        postRepository.delete(post);
    }

}
