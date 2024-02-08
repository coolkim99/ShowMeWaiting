package com.example.showmewaiting.repository;

import com.example.showmewaiting.domain.Item;
import com.example.showmewaiting.domain.Store;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if(item.getId() == null) {
            em.persist(item);
        }
        else {
            em.merge(item);
        }
    }

    public boolean delete(Item item) {
        if(findOne(item.getId()) != null) {
            em.remove(item);
            return true;
        }
        else {
            return false;
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    //가게 메뉴 가져오기 위한 메서드
    public List<Item> findStoreItem(Long storeId) {
        return em.createQuery("select i from Item i" +
                " where i.store.id = :storeId", Item.class)
                .setParameter("storeId", storeId)
                .getResultList();
    }

}
