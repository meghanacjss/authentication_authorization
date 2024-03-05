package com.example.autherization_authentication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
@Id
@GeneratedValue
    private int userId;
    private String username;
    private String password;
    private String email;
    private String roles;

}
