package com.example.showmewaiting.api;

import com.example.showmewaiting.domain.User;
import com.example.showmewaiting.domain.UserType;
import com.example.showmewaiting.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @PostMapping("/api/user")
    public CreateUserResponse saveUser(@RequestBody @Valid CreateUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setType(request.getType());
        user.setPassword(request.getPassword());
        user.setName(request.getName());

        Long id = userService.join(user);
        return new CreateUserResponse(id);
    }

    @Data
    static class CreateUserResponse {
        private Long id;

        public CreateUserResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    static class CreateUserRequest {
        @NotEmpty
        private String email;
        private UserType type;
        private String name;
        private String password;
    }
}
