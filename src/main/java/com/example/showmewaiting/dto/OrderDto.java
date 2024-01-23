package com.example.showmewaiting.dto;

import com.example.showmewaiting.domain.OrderStatus;
import jakarta.persistence.EntityManager;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private Long orderId;
    private Long userId;
    private Long storeId;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;

    public OrderDto(Long orderId, Long userId, Long storeId, LocalDateTime orderDate, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.userId = userId;
        this.storeId = storeId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }
}
