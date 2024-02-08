package com.example.showmewaiting.api;

import com.example.showmewaiting.domain.Order;
import com.example.showmewaiting.domain.Store;
import com.example.showmewaiting.domain.User;
import com.example.showmewaiting.dto.OrderDto;
import com.example.showmewaiting.dto.StoreOrderDto;
import com.example.showmewaiting.repository.OrderRepository;
import com.example.showmewaiting.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

        Long id = orderService.order(userId, itemId, storeId, count);
        return new CreateOrderResponse(id);
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
