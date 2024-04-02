package com.infinity.devmarket.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Username is required")
    //@Size(min = 2, max = 255, message = "Username size should be from 2 to 255")
    @Column(name = "username")
    private String username;
    @Email(message = "Email should be valid")
    //@Size(max = 255, message = "Email size should be to 255")
    @Column(name = "email")
    private String email;
    @NotEmpty(message = "Password is required")
    //@Size(min = 4, max = 60, message = "Password size should be from 4 to 60")
    @Column(name = "password")
    private String password;
    @NotEmpty(message = "Private key is required")
    //@Size(max = 255, message = "Private key size should be to 255")
    @Column(name = "private_key")
    private String privateKey;

    public Person() { }

    public Person(Long id, String username, String email, String password, String privateKey) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.privateKey = privateKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", privateKey='" + privateKey + '\'' +
                '}';
    }
}
