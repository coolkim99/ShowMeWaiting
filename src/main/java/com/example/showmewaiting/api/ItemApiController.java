package com.example.showmewaiting.api;

import com.example.showmewaiting.domain.Item;
import com.example.showmewaiting.domain.Store;
import com.example.showmewaiting.service.ItemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

        Long id = itemService.saveItem(item, storeId);
        return new CreateItemResponse(id);
    }

    @DeleteMapping("/api/items/delete")
    public DeleteItemResponse deleteItem(@RequestBody @Valid DeleteItemRequest request) {
        return new DeleteItemResponse(itemService.deleteItem(request.getId()));
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

    @Data
    static class DeleteItemRequest {
        private Long id;

    }

    @Data
    static class DeleteItemResponse {
        private boolean result;

        public DeleteItemResponse(boolean result) {
            this.result = result;
        }

    }
}
