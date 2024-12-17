package com.proyecto.moldy_carrots.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.moldy_carrots.exception.BadRequestException;
import com.proyecto.moldy_carrots.users.model.User;
import com.proyecto.moldy_carrots.users.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User validateIfUserIsNonExisting(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findById(long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateUser(long userId, User userDetails) throws BadRequestException {
        User user = findById(userId);
        if (user == null) {
            throw new BadRequestException("User not found");
        }

        user.setUsername(userDetails.getUsername());
        user.setProfileImage(userDetails.getProfileImage());
        user.setName(userDetails.getName());
        user.setLastname(userDetails.getLastname());
        user.setEmail(userDetails.getEmail());
        String encryptedPassword = passwordEncoder.encode(userDetails.getPassword());
        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        userRepository.delete(user);
    }

    public User partialyUpdateUser(Long id, User userDetails) {
        return userRepository.save(userDetails);
    }

}
