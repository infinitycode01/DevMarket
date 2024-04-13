package com.infinity.devmarket.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Логін є обов'язковим полем")
    @Size(min = 2, max = 20, message = "Довжина логіну має бути від 2 до 255 символів")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "Пароль є обов'язковим полем")
    @Size(min = 4, max = 60, message = "Довжина паролю має бути від 4 до 60 символів")
    @Column(name = "password")
    private String password;

    @NotEmpty(message = "Адрес гаманця є обов'язковим полем")
    @Size(max = 255, message = "Адрес гаманця має бути меншим")
    @Column(name = "wallet_address")
    private String walletAddress;

    @Column(name = "role")
    private String role;

    public Person() { }

    public Person(Long id, String username, String password, String walletAddress) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.walletAddress = walletAddress;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }
}
