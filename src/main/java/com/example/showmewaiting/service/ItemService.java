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
public class ItemService {

    private final ItemRepository itemRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public Long saveItem(Item item, Long storeId) {
        Store store = storeRepository.findById(storeId);
        Item newItem = Item.createItem(item, item.getName(), store, item.getPrice(), item.getStockQuantity());
        return newItem.getId();
    }

    @Transactional
    public Item updateItem(Long id, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(id);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);

        return findItem;
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public List<Item> findStoreItems(Long storeId) {
        return itemRepository.findStoreItem(storeId);
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
