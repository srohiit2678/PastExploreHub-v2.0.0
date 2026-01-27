package com.pastexplorehub.repository;

import com.pastexplorehub.entity.User;
import com.pastexplorehub.model.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEnrollId(String enrollId);
    List<User> findByRole(UserRole role);
}