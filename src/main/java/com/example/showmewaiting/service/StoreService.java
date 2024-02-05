package com.example.showmewaiting.service;

import com.example.showmewaiting.domain.Item;
import com.example.showmewaiting.domain.Store;
import com.example.showmewaiting.repository.ItemRepository;
import com.example.showmewaiting.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {

    private final ItemRepository itemRepository;
    private final StoreRepository storeRepository;

    //메뉴조회
    public List<Item> getMenuList(Long storeId) {
        return itemRepository.findStoreItem(storeId);
    }

    public List<Store> getStoreList() {
        return storeRepository.findAll();
    }

    public Store getStore(Long storeId) {
        return storeRepository.findById(storeId);
    }

}
