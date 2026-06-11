package com.gabrielbenini.pulseapi.service;

import com.gabrielbenini.pulseapi.exception.ResourceNotFoundException;
import com.gabrielbenini.pulseapi.model.dtos.LoginRequestDTO;
import com.gabrielbenini.pulseapi.model.dtos.LoginResponseDTO;
import com.gabrielbenini.pulseapi.model.dtos.UserRequestDTO;
import com.gabrielbenini.pulseapi.model.dtos.UserResponseDTO;
import com.gabrielbenini.pulseapi.model.entities.User;
import com.gabrielbenini.pulseapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){

        User user = userRequestDTO.toEntity();
        userRepository.save(user);

        return UserResponseDTO.fromEntity(user);
    }

    public List<UserResponseDTO> listAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::fromEntity)
                .toList();
    }

    public UserResponseDTO findUserById(Long userId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return UserResponseDTO.fromEntity(user);
    }

    public UserResponseDTO updateUserById(Long userId, UserRequestDTO requestDTO){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        user.setUsername(requestDTO.username());
        user.setEmail(requestDTO.email());
        user.setPassword(requestDTO.password());
        user.setImageUrl(requestDTO.imageUrl());

        userRepository.save(user);

        return UserResponseDTO.fromEntity(user);
    }

    public void deleteUserById(Long userId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        userRepository.delete(user);
    }
}

