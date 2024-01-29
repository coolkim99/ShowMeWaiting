package com.example.showmewaiting.dto;

import com.example.showmewaiting.domain.UserType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddUserRequest {
    private String email;
    private String password;
    private UserType type;
    private String name;
}
