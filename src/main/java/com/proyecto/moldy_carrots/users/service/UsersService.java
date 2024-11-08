package com.proyecto.moldy_carrots.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.moldy_carrots.users.model.User;
import com.proyecto.moldy_carrots.users.repository.UserRepository;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User validateIfUserIsNonExisting(String username) {
        return userRepository.findByUsername(username);
    }
}
