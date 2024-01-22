package com.example.showmewaiting.dto;

import lombok.Data;

@Data
public class ItemQueryDto {
    private Long itemId;
    private Long storeId;
    private int price;
    private String name;
    private int stockQuantity;

    public ItemQueryDto(Long itemId, Long storeId, int price, String name, int stockQuantity) {
        this.itemId = itemId;
        this.storeId = storeId;
        this.price = price;
        this.name = name;
        this.stockQuantity = stockQuantity;
    }
}
