package com.example.showmewaiting.api;

import com.example.showmewaiting.domain.OrderStatus;
import com.example.showmewaiting.dto.StoreOrderDto;
import com.example.showmewaiting.repository.OrderRepository;
import com.example.showmewaiting.service.OrderService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @PostMapping("/api/{storeId}/order/{itemId}")
    public CreateOrderResponse order(@RequestBody @Valid CreateOrderRequest request,
                                     @PathVariable("storeId") Long storeId,
                                     @PathVariable("itemId") Long itemId) {
        Long userId = request.getUserId();
        int count = Math.toIntExact(request.getCount());

        //orderService.order(userId, itemId, storeId, count);
        return orderService.order(userId, itemId, storeId, count);
    }

    @GetMapping("/api/getordering/{storeId}")
    public List<StoreOrderDto> getOrdering(@PathVariable("storeId") Long storeId) {
        return orderRepository.findOrdering(storeId);
    }

    @GetMapping("/api/getdone/{storeId}")
    public List<StoreOrderDto> getDone(@PathVariable("storeId") Long storeId) {

        List<StoreOrderDto> all = orderService.getDone(storeId);
        return all;
    }

    @GetMapping("/api/getOrders/{userId}")
    public List<StoreOrderDto> getMyOrder(@PathVariable("userId") Long userId) {

        List<StoreOrderDto> all = orderService.getMyOrder(userId);
        return all;
    }

    @PutMapping("/api/cancle")
    public StoreApiController.UpdateOrderResponse updateOrder(@RequestBody @Valid StoreApiController.UpdateOrderRequest request) {
        Long id = request.getId();
        orderService.cancelOrder(id);
        return new StoreApiController.UpdateOrderResponse(id);
    }

    @Data
    static class CreateOrderRequest {
        private Long userId;
        private Long count;
    }

    @Data
    public static class CreateOrderResponse {
        private Long id;
        private String itemName;
        private int count;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private String storeName;

        public CreateOrderResponse(Long id, String itemName, int count, LocalDateTime orderDate, OrderStatus orderStatus, String storeName) {
            this.id = id;
            this.itemName = itemName;
            this.count = count;
            this.orderDate = orderDate;
            this.orderStatus = orderStatus;
            this.storeName = storeName;
        }

    }
}
