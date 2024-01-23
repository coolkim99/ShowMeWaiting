package com.example.showmewaiting.api;

import com.example.showmewaiting.domain.Item;
import com.example.showmewaiting.domain.Store;
import com.example.showmewaiting.repository.StoreRepository;
import com.example.showmewaiting.service.ItemService;
import com.example.showmewaiting.service.StoreService;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class StoreApiController {

    private final StoreService storeService;

    //메뉴 리스트 보여주기
    @GetMapping("/api/{storeId}")
    public List<ItemDto> storeMenu(@PathVariable("storeId") Long id) {
        List<Item> all = storeService.getMenuList(id);
        List<ItemDto> collect = all.stream()
                .map(o -> new ItemDto(o))
                .collect(Collectors.toList());

        return collect;
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

}
