package com.proyecto.moldy_carrots.users.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

import com.proyecto.moldy_carrots.reviews.model.Review;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Size(min = 5, max = 15)
    @Column(name = "username", nullable = false)
    private String username;

    @Lob
    @Column(name = "profileImage", nullable = false, columnDefinition = "bytea")
    private byte[] profileImage;

    @Size(min = 1, max = 30)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(min = 1, max = 30)
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Size(min = 8)
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL) 
    private List<Review> reviews;
}
