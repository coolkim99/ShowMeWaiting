//package com.example.showmewaiting;
//
//import com.example.showmewaiting.domain.Authority;
//import com.example.showmewaiting.domain.UserType;
//import com.example.showmewaiting.dto.AddUserRequest;
//import com.example.showmewaiting.service.UserService;
//import jakarta.annotation.PostConstruct;
//import jakarta.persistence.EntityManager;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import static com.example.showmewaiting.domain.UserType.CONSUMER;
//import static com.example.showmewaiting.domain.UserType.STORE;
//
//@Component
//@RequiredArgsConstructor
//public class InitDB {
//
//    private final InitService initService;
//
//    @PostConstruct
//    public void init() {
//        initService.dbInit1();
//    }
//
//    @Component
//    @Transactional
//    @RequiredArgsConstructor
//    static class InitService {
//        private final EntityManager em;
//        private final UserService userService;
//
//        public void dbInit1() {
//            AddUserRequest user = createUser("seaone.c1@gmail.com", "sdkgjh", "seaone", CONSUMER, Authority.ROLE_USER);
//            AddUserRequest user1 = createUser("mega@gmail.com", "mega", "mega", STORE, Authority.ROLE_USER);
//            AddUserRequest user2 = createUser("ediya@gmail.com", "ediya", "ediya", STORE, Authority.ROLE_USER);
//
//            userService.join(user);
//            userService.join(user1);
//            userService.join(user2);
//
//        }
//
//        private AddUserRequest createUser(String email, String password, String name, UserType type, Authority authority) {
//            AddUserRequest user = new AddUserRequest();
//            user.setEmail(email);
//            user.setType(type);
//            user.setPassword(password);
//            user.setName(name);
//            user.setAuthority(authority);
//            return user;
//        }
//    }
//}
