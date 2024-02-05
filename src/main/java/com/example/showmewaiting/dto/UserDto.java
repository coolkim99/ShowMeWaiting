package com.example.showmewaiting.dto;

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

    private Long id;
    private String email;
    private String name;
    private UserType type;

}
