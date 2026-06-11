package com.gabrielbenini.pulseapi.service;

import com.gabrielbenini.pulseapi.model.dtos.FollowResponseDTO;
import com.gabrielbenini.pulseapi.model.entities.Follow;
import com.gabrielbenini.pulseapi.model.entities.User;
import com.gabrielbenini.pulseapi.repository.FollowRepository;
import com.gabrielbenini.pulseapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowResponseDTO follow(Long followerId, Long followingId) {

        if (followerId.equals(followingId)) {
            throw new RuntimeException("User cannot follow himself");
        }

        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Follower not found"));

        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean alreadyFollowing = followRepository.existsByFollowerAndFollowing(follower, following);

        if (!alreadyFollowing) {
            Follow follow = new Follow();
            follow.setFollower(follower);
            follow.setFollowing(following);
            followRepository.save(follow);
        }

        long followersCount = followRepository.countByFollowing(following);

        return new FollowResponseDTO(followingId, true, followersCount);
    }

    @Transactional
    public FollowResponseDTO unfollow(Long followerId, Long followingId) {

        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Follower not found"));

        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        followRepository.deleteByFollowerAndFollowing(follower, following);

        long followersCount = followRepository.countByFollowing(following);

        return new FollowResponseDTO(followingId, false, followersCount);
    }
}