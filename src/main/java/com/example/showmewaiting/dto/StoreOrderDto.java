package com.example.showmewaiting.dto;

import com.example.showmewaiting.api.StoreApiController;
import com.example.showmewaiting.domain.Item;
import com.example.showmewaiting.domain.OrderStatus;
import com.example.showmewaiting.domain.Store;
import com.example.showmewaiting.domain.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoreOrderDto {
    private Long id;
    private String userName;
    private String storeName;
    private String itemName;
    private int count;
    private int orderPrice;
    private LocalDateTime orderDate;
    private OrderStatus status;

    public StoreOrderDto(Long id, String userName, String storeName, String itemName, int count, int orderPrice, LocalDateTime orderDate, OrderStatus status) {
        this.id = id;
        this.userName = userName;
        this.storeName = storeName;
        this.itemName = itemName;
        this.count = count;
        this.orderPrice = orderPrice;
        this.orderDate = orderDate;
        this.status = status;
    }
}
