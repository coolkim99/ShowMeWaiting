package com.example.showmewaiting.api;

import com.example.showmewaiting.domain.Response;
import com.example.showmewaiting.domain.UserType;
import com.example.showmewaiting.dto.AddUserRequest;
import com.example.showmewaiting.dto.UserSignInRequestDto;
import com.example.showmewaiting.jwt.JwtToken;
import com.example.showmewaiting.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @PostMapping("/api/join")
    public ResponseEntity<Response<CreateUserResponse>> saveUser(@RequestBody @Valid AddUserRequest request) {
        AddUserRequest user = new AddUserRequest();
        user.setEmail(request.getEmail());
        user.setType(request.getType());
        user.setPassword(request.getPassword());
        user.setName(request.getName());

        System.out.println("comes in?");

        Long id = userService.join(user);
        return ResponseEntity.ok().body(Response.success(new CreateUserResponse(id)));
    }

    @PostMapping("/api/login")
    public ResponseEntity<JwtToken> login(@RequestBody UserSignInRequestDto request) throws Exception {
        return ResponseEntity.ok().body(userService.login(request));
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
