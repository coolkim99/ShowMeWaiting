package com.example.showmewaiting.api;

import com.example.showmewaiting.domain.Authority;
import com.example.showmewaiting.domain.Response;
import com.example.showmewaiting.domain.User;
import com.example.showmewaiting.domain.UserType;
import com.example.showmewaiting.dto.AddUserRequest;
import com.example.showmewaiting.dto.TokenRequestDto;
import com.example.showmewaiting.dto.UserDto;
import com.example.showmewaiting.dto.UserSignInRequestDto;
import com.example.showmewaiting.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        user.setAuthority(Authority.ROLE_USER);

        System.out.println("comes in?");

        Long id = userService.join(user);
        return ResponseEntity.ok().body(Response.success(new CreateUserResponse(
                id, user.getEmail(), user.getName(), user.getType())));
    }

    @PostMapping("/api/login")
    public ResponseEntity<Response<UserDto>> login(@RequestBody UserSignInRequestDto request) throws Exception {
        return ResponseEntity.ok().body(Response.success(userService.login(request)));
    }

    @PostMapping("/api/check")
    public ResponseEntity<UserDto> check(@RequestBody UserCheckRequest request) {
        System.out.println(request);
        User user = userService.check((Long) request.getId());
        System.out.println("check fin");
        UserDto userDto = new UserDto(user.getId(), user.getEmail(), user.getName(), user.getType());
        return ResponseEntity.ok().body(userDto);
    }

//    @PostMapping("/api/logout")
//    public ResponseEntity<Void> logout(@RequestBody TokenRequestDto request) {
//        userService.logout(request);
//        return new ResponseEntity(HttpStatus.OK);
//    }



    @Data
    static class CreateUserResponse {
        private Long id;
        private String email;
        private UserType type;
        private String name;

        public CreateUserResponse(Long id, String email, String name, UserType type) {
            this.id = id;
            this.email = email;
            this.name = name;
            this.type = type;
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

    @Data
    static class UserCheckRequest {
        private Long id;
    }
}
