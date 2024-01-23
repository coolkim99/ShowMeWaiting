package com.example.showmewaiting.api;

import com.example.showmewaiting.domain.Order;
import com.example.showmewaiting.domain.User;
import com.example.showmewaiting.repository.OrderRepository;
import com.example.showmewaiting.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping("/api/{storeId}/order/{itemId}")
    public CreateOrderResponse order(@RequestBody @Valid CreateOrderRequest request,
                                     @PathVariable("storeId") Long storeId,
                                     @PathVariable("itemId") Long itemId) {
        Long userId = request.getUserId();
        int count = Math.toIntExact(request.getCount());

        Long id = orderService.order(userId, itemId, storeId, count);
        return new CreateOrderResponse(id);
    }

    @Data
    static class CreateOrderRequest {
        private Long userId;
        private Long count;
    }

    @Data
    static class CreateOrderResponse {
        private Long id;

        public CreateOrderResponse(Long id) {
            this.id = id;
        }
    }
}
