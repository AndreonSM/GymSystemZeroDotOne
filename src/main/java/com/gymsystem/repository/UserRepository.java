package com.gymsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymsystem.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom queries, if needed
	Optional<User> findByUsername(String username);
}
