package com.example.showmewaiting.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "users")
@Getter @Setter
public class User {

    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType type; //STORE, CONSUMER
}
