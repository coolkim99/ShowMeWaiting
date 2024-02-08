package com.example.showmewaiting.dto;

import com.example.showmewaiting.domain.User;
import com.example.showmewaiting.domain.UserType;
import lombok.Data;

@Data
public class UserDto {
    public UserDto(Long id, String email, String name, UserType type) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.type = type;
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.type = user.getType();
    }

    private Long id;
    private String email;
    private String name;
    private UserType type;

}
