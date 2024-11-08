package com.proyecto.moldy_carrots.users.model;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import java.sql.Types;

import lombok.Data;

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

    @JdbcTypeCode(Types.VARBINARY)
    @Column(name = "profileImage", nullable = true, columnDefinition = "bytea")
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

}
