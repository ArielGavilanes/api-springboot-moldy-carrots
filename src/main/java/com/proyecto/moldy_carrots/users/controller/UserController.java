package com.proyecto.moldy_carrots.users.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.proyecto.moldy_carrots.exception.BadRequestException;
import com.proyecto.moldy_carrots.users.dto.UpdateUserDTO;
import com.proyecto.moldy_carrots.users.dto.UserDTO;
import com.proyecto.moldy_carrots.users.model.User;
import com.proyecto.moldy_carrots.users.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("")
    @ResponseBody
    public ResponseEntity<User> updateUser(@Valid @ModelAttribute UserDTO userDto,
            @RequestPart("profileImage") MultipartFile profileImage) throws IOException, BadRequestException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        user.setUsername(userDto.getUsername());
        user.setProfileImage(profileImage.getBytes());
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        User updatedUser = userService.updateUser(user.getUserId(), user);

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("")
    @ResponseBody
    public ResponseEntity<User> partialyUpdateUser(@Valid @ModelAttribute UpdateUserDTO userDto,
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        if (userDto.getUsername() != null) {
            user.setUsername(userDto.getUsername());
        }

        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }

        if (profileImage != null) {
            user.setProfileImage(profileImage.getBytes());
        }

        User updatedUser = userService.partialyUpdateUser(user.getUserId(), user);
        return ResponseEntity.ok(updatedUser);
    }
}
