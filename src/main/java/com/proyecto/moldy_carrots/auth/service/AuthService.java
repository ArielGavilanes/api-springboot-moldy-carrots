package com.proyecto.moldy_carrots.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.moldy_carrots.exception.BadRequestException;
import com.proyecto.moldy_carrots.users.model.User;
import com.proyecto.moldy_carrots.users.service.UsersService;

@Service
public class AuthService {

    @Autowired
    private UsersService usersService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User newUser) throws BadRequestException {
        User existingUser = usersService.validateIfUserIsNonExisting(newUser.getUsername());
        if (existingUser != null) {
            throw new BadRequestException("User already registered");
        }

        String encryptedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encryptedPassword);
        System.out.println("contrasena encriptada");

        return usersService.createUser(newUser);
    }

}
