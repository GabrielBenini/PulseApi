package com.gabrielbenini.pulseapi.controller;

import com.gabrielbenini.pulseapi.model.dtos.CommentRequestDTO;
import com.gabrielbenini.pulseapi.model.dtos.CommentResponseDTO;
import com.gabrielbenini.pulseapi.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody @Valid CommentRequestDTO commentRequestDTO){
        CommentResponseDTO responseDTO = commentService.createComment(commentRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
