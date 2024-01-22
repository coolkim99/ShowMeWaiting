package com.example.showmewaiting.service;

import com.example.showmewaiting.domain.Item;
import com.example.showmewaiting.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long saveItem(Item item) {
        itemRepository.save(item);
        return null;
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
