package com.example.showmewaiting.repository;

import com.example.showmewaiting.domain.Store;
import com.example.showmewaiting.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StoreRepository {

    private final EntityManager em;

    public void save(Store store) {
        em.persist(store);
    }

}
