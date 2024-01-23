package com.example.showmewaiting.api;

import com.example.showmewaiting.domain.Item;
import com.example.showmewaiting.domain.Store;
import com.example.showmewaiting.service.ItemService;
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
public class ItemApiController {

    private final ItemService itemService;

    @PostMapping("/api/{storeId}/items/new")
    public CreateItemResponse saveItem(@PathVariable("storeId") Long storeId,
                                       @RequestBody @Valid CreateItemRequest request) {
        Item item = new Item();

        item.setName(request.getName());
        item.setPrice(request.getPrice());
        item.setStockQuantity(request.getStockQuantity());

        Long id = itemService.saveItem(item, storeId);
        return new CreateItemResponse(id);
    }

    @Data
    static class CreateItemResponse {
        private Long id;

        public CreateItemResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    static class CreateItemRequest {
        @NotEmpty
        private String name;

        private int price;
        private int stockQuantity;
        private Store store;

    }
}
