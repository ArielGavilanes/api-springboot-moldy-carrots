package com.proyecto.moldy_carrots.auth.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proyecto.moldy_carrots.auth.dto.LoginDTO;
import com.proyecto.moldy_carrots.auth.dto.RegisterDTO;
import com.proyecto.moldy_carrots.auth.jwt.JwtService;
import com.proyecto.moldy_carrots.auth.response.LoginResponse;
import com.proyecto.moldy_carrots.auth.service.AuthService;
import com.proyecto.moldy_carrots.exception.BadRequestException;
import com.proyecto.moldy_carrots.users.model.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<User> registerUser(@Valid @ModelAttribute RegisterDTO userDto,
            @RequestParam("profileImage") MultipartFile profileImage) throws IOException, BadRequestException {

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setProfileImage(profileImage.getBytes());
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        User newUser = authService.registerUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginDTO userDetails) {
        User authenticatedUser = authService.loginUser(userDetails);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);

    }

    @GetMapping("/profile")
    @ResponseBody
    public ResponseEntity<User> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User authenticatedUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(authenticatedUser);
    }

}
