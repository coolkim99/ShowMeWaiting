package com.example.showmewaiting.service;

import com.example.showmewaiting.domain.Store;
import com.example.showmewaiting.domain.User;
import com.example.showmewaiting.dto.AddUserRequest;
import com.example.showmewaiting.repository.StoreRepository;
import com.example.showmewaiting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    //회원가입
    @Transactional
    public Long join(User user) {

        validateDuplicateMember(user);
        userRepository.save(user);

        //store이면 store테이블에 저장
        String userType = String.valueOf(user.getType());
        if(userType.equals("STORE")) {
            Store store = changeToStore(user);
            storeRepository.save(store);
        }

        return user.getId();
    }

    private Store changeToStore(User user) {
        Store store = new Store();
        store.setId(user.getId());
        store.setName(user.getName());
        return store;
    }

    private void validateDuplicateMember(User user) {
        List<User> findMembers = userRepository.findByEmail(user.getEmail());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
