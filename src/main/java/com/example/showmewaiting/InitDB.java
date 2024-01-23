package com.example.showmewaiting;

import com.example.showmewaiting.domain.Item;
import com.example.showmewaiting.domain.Store;
import com.example.showmewaiting.domain.User;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.example.showmewaiting.domain.UserType.CONSUMER;
import static com.example.showmewaiting.domain.UserType.STORE;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            User user = new User();
            user.setEmail("seaone.c1@gmail.com");
            user.setType(CONSUMER);
            user.setPassword("sdkjfh");
            user.setName("seaone");

            User user2 = new User();
            user2.setEmail("mega@gmail.com");
            user2.setType(STORE);
            user2.setPassword("sdkjfhfdg");
            user2.setName("mega");

            User user3 = new User();
            user3.setEmail("ediya@gmail.com");
            user3.setType(STORE);
            user3.setPassword("sjfhfdg");
            user3.setName("ediya");

            em.persist(user);
            em.persist(user2);
            em.persist(user3);

            Store store = new Store();
            store.setName(user2.getName());
            store.setId(user2.getId());

            Store store2 = new Store();
            store2.setName(user3.getName());
            store2.setId(user3.getId());

            em.persist(store);
            em.persist(store2);

            Item item = new Item();
            item.setPrice(10000);
            item.setStore(store);
            item.setStockQuantity(3);
            item.setName("americano");

            em.persist(item);

        }
    }
}
