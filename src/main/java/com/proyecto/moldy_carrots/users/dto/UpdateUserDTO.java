package com.proyecto.moldy_carrots.users.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserDTO {
    @Size(min = 5, max = 15, message = "Username must be between 5 and 15 characters")
    private String username;

    private MultipartFile profileImage;

    @Email(message = "Email should be valid")
    private String email;
}
