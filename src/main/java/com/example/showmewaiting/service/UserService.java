package com.example.showmewaiting.service;

import com.example.showmewaiting.domain.Store;
import com.example.showmewaiting.domain.User;
import com.example.showmewaiting.dto.AddUserRequest;
import com.example.showmewaiting.dto.UserSignInRequestDto;
import com.example.showmewaiting.repository.StoreRepository;
import com.example.showmewaiting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    private void validateEmail(UserSignInRequestDto user) {
        List<User> users = userRepository.findByEmail(user.getEmail());

        if (users.isEmpty()) {
            throw new IllegalArgumentException("가입되지 않은 이메일 입니다.");
        }
    }

    @Transactional
    public boolean login(UserSignInRequestDto userDto) {
        validateEmail(userDto);

        List<User> userList = userRepository.findByEmail(userDto.getEmail());
        User user = userList.get(0);
        

        boolean checkPassword = bCryptPasswordEncoder.matches(userDto.getPassword(), user.getPassword());
        if(!checkPassword) {
            throw new IllegalStateException("잘못된 비밀번호 입니다.");
        }

        return true;
    }
}
