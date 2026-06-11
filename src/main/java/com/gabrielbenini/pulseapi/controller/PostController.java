package com.gabrielbenini.pulseapi.controller;

import com.gabrielbenini.pulseapi.model.dtos.LikeResponseDTO;
import com.gabrielbenini.pulseapi.model.dtos.PostRequestDTO;
import com.gabrielbenini.pulseapi.model.dtos.PostResponseDTO;
import com.gabrielbenini.pulseapi.service.LikeService;
import com.gabrielbenini.pulseapi.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody @Valid PostRequestDTO requestDTO) {

        PostResponseDTO responseDTO = postService.createPost(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePostById(@PathVariable("postId") Long postId, @RequestParam Long userId) {

        postService.deletePostById(postId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getPosts(@RequestParam Long userId) {

        List<PostResponseDTO> responseDTO = postService.getPosts(userId);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<LikeResponseDTO> likePost(
            @PathVariable("postId") Long postId,
            @RequestParam Long userId
    ) {
        return ResponseEntity.ok(likeService.likePost(postId, userId));
    }

    @DeleteMapping("/{postId}/unlike")
    public ResponseEntity<LikeResponseDTO> unlikePost(
            @PathVariable("postId") Long postId,
            @RequestParam Long userId
    ) {
        return ResponseEntity.ok(likeService.unlikePost(postId, userId));
    }

}
