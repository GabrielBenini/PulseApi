package com.gabrielbenini.pulseapi.controller;

import com.gabrielbenini.pulseapi.model.dtos.*;
import com.gabrielbenini.pulseapi.model.entities.User;
import com.gabrielbenini.pulseapi.repository.UserRepository;
import com.gabrielbenini.pulseapi.service.FollowService;
import com.gabrielbenini.pulseapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final FollowService followService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO requestDTO){

        UserResponseDTO responseDTO = userService.createUser(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listAllUsers(){

        List<UserResponseDTO> responseDTO = userService.listAllUsers();
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable("userId") Long userId){

        UserResponseDTO responseDTO = userService.findUserById(userId);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUserById(@PathVariable("userId") Long userId, @RequestBody @Valid UserRequestDTO requestDTO){

        UserResponseDTO responseDTO = userService.updateUserById(userId, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") Long userId) {

        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest){

        Optional<User> userOpt = userRepository
                .findByEmailAndPassword(loginRequest.email(), loginRequest.password());

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userOpt.get();

        LoginResponseDTO response = new LoginResponseDTO(
                user.getId(),
                user.getEmail()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userId}/follow")
    public ResponseEntity<FollowResponseDTO> followUser(
            @PathVariable("userId") Long userId,
            @RequestParam Long followerId
    ) {
        return ResponseEntity.ok(
                followService.follow(followerId, userId)
        );
    }

    @DeleteMapping("/{userId}/unfollow")
    public ResponseEntity<FollowResponseDTO> unfollowUser(
            @PathVariable("userId") Long userId,
            @RequestParam Long followerId
    ) {
        return ResponseEntity.ok(
                followService.unfollow(followerId, userId)
        );
    }
}
