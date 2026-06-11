package com.gabrielbenini.pulseapi.repository;

import com.gabrielbenini.pulseapi.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);
}
