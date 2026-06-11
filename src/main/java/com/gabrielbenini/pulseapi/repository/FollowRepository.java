package com.gabrielbenini.pulseapi.repository;

import com.gabrielbenini.pulseapi.model.entities.Follow;
import com.gabrielbenini.pulseapi.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByFollowerAndFollowing(User follower, User following);

    long countByFollowing(User following);

    void deleteByFollowerAndFollowing(User follower, User following);
}



