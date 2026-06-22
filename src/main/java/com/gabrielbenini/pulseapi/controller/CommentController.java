package com.gabrielbenini.pulseapi.controller;

import com.gabrielbenini.pulseapi.model.dtos.CommentRequestDTO;
import com.gabrielbenini.pulseapi.model.dtos.CommentResponseDTO;
import com.gabrielbenini.pulseapi.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentResponseDTO> createComment(
            @PathVariable("postId") Long postId,
            @RequestBody @Valid CommentRequestDTO commentRequestDTO
    ) {
        CommentResponseDTO responseDTO = commentService.createComment(commentRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByPost(
            @PathVariable("postId") Long postId
    ) {
        List<CommentResponseDTO> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }
}