package com.proyecto.moldy_carrots.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.moldy_carrots.auth.dto.LoginDTO;
import com.proyecto.moldy_carrots.exception.BadRequestException;
import com.proyecto.moldy_carrots.users.model.User;
import com.proyecto.moldy_carrots.users.service.UserService;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public User registerUser(User newUser) throws BadRequestException {
        User existingUser = userService.validateIfUserIsNonExisting(newUser.getUsername());
        if (existingUser != null) {
            throw new BadRequestException("User already registered");
        }

        String encryptedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encryptedPassword);

        return userService.createUser(newUser);
    }

    public User loginUser(LoginDTO userDetails) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword()));

        return userService.findByUsername(userDetails.getUsername());
    }

    public User getProfile(String username) {
        return userService.findByUsername(username);
    }

}
