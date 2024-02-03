package com.example.showmewaiting.service;

import com.example.showmewaiting.domain.Store;
import com.example.showmewaiting.domain.User;
import com.example.showmewaiting.dto.AddUserRequest;
import com.example.showmewaiting.dto.UserSignInRequestDto;
import com.example.showmewaiting.exception.ErrorCode;
import com.example.showmewaiting.exception.UserException;
import com.example.showmewaiting.jwt.JwtToken;
import com.example.showmewaiting.jwt.JwtTokenProvider;
import com.example.showmewaiting.repository.StoreRepository;
import com.example.showmewaiting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    //회원가입
    @Transactional
    public Long join(AddUserRequest user) {
        validateDuplicateMember(user);

        Long id = userRepository.save(User.builder()
                .email(user.getEmail())
                .password(bCryptPasswordEncoder.encode(user.getPassword()))
                .name(user.getName())
                .type(user.getType())
                .build());

        User curr = userRepository.findOne(id);

        //store이면 store테이블에 저장
        String userType = String.valueOf(curr.getType());
        if(userType.equals("STORE")) {
            Store store = changeToStore(curr);
            storeRepository.save(store);
        }

        return id;
    }

    private Store changeToStore(User user) {
        Store store = new Store();
        store.setId(user.getId());
        store.setName(user.getName());
        return store;
    }

    private void validateDuplicateMember(AddUserRequest user) {
        List<User> findMembers = userRepository.findByEmail(user.getEmail());
        if(!findMembers.isEmpty()) {
            throw new UserException(ErrorCode.DUPLICATED_USER_NAME, String.format("Email :",user.getEmail()));
        }
    }

    private void validateEmail(UserSignInRequestDto user) {
        List<User> users = userRepository.findByEmail(user.getEmail());

        if (users.isEmpty()) {
            throw new IllegalArgumentException("가입되지 않은 이메일 입니다.");
        }
    }

//    @Transactional
//    public boolean login(UserSignInRequestDto userDto) {
//        validateEmail(userDto);
//
//        List<User> userList = userRepository.findByEmail(userDto.getEmail());
//        User user = userList.get(0);
//
//
//        boolean checkPassword = bCryptPasswordEncoder.matches(userDto.getPassword(), user.getPassword());
//        if(!checkPassword) {
//            throw new IllegalStateException("잘못된 비밀번호 입니다.");
//        }
//
//        return true;
//    }

    @Transactional
    public JwtToken login(UserSignInRequestDto userDto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());

        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);

        return jwtToken;
    }
}
