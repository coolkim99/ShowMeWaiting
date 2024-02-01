package com.example.showmewaiting.api;

import com.example.showmewaiting.domain.Item;
import com.example.showmewaiting.service.OrderService;
import com.example.showmewaiting.service.StoreService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class StoreApiController {

    private final StoreService storeService;
    private final OrderService orderService;

    //메뉴 리스트 보여주기
    @GetMapping("/api/{storeId}")
    public List<ItemDto> storeMenu(@PathVariable("storeId") Long id) {
        List<Item> all = storeService.getMenuList(id);
        List<ItemDto> collect = all.stream()
                .map(o -> new ItemDto(o))
                .collect(Collectors.toList());

        return collect;
    }

    @PutMapping("/api/done")
    public UpdateOrderResponse updateOrder(@RequestBody @Valid UpdateOrderRequest request) {
        Long id = request.getId();
        orderService.orderDone(id);
        return new UpdateOrderResponse(id);
    }

    @Data
    @Getter
    static class ItemDto {
        private Long itemId;
        private String name;
        private int price;

        public ItemDto(Item item) {
            itemId = item.getId();
            name = item.getName();
            price = item.getPrice();
        }
    }

    @Data
    static class UpdateOrderResponse {
        private Long id;

        public UpdateOrderResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    static class UpdateOrderRequest {
        @NotNull
        private Long id;
    }

}
