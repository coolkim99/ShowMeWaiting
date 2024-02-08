package com.example.showmewaiting.dto;

import com.example.showmewaiting.domain.Item;

public class ItemDto {
    private Long id;
    private Long storeId;
    private String name;
    private int price;

    public ItemDto(Item item) {
        this.id = item.getId();
        this.storeId =  item.getStore().getId();
        this.name =  item.getName();
        this.price =  item.getPrice();
    }
}
