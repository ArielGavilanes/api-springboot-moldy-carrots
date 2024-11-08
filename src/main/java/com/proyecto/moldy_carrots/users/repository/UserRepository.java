package com.proyecto.moldy_carrots.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.moldy_carrots.users.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
