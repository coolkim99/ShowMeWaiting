package com.example.showmewaiting.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "item")
@Getter @Setter
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    private String name;
    private int price;

    @Column(name = "stock_quantity")
    private int stockQuantity;


    //생성 메서드
    public static Item createItem(Item item, String name, Store store, int price, int stockQuantity) {
        Item storeItem = new Item();
        storeItem.setStore(store);
        storeItem.setName(name);
        storeItem.setStockQuantity(stockQuantity);
        storeItem.setPrice(price);

        return storeItem;
    }

    //생성자
    public void setStore(Store store) {
        this.store = store;
        store.getMenuItems().add(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }


}
