package com.example.showmewaiting.service;

import com.example.showmewaiting.domain.*;
import com.example.showmewaiting.dto.StoreOrderDto;
import com.example.showmewaiting.repository.ItemRepository;
import com.example.showmewaiting.repository.OrderRepository;
import com.example.showmewaiting.repository.StoreRepository;
import com.example.showmewaiting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public Long order(Long uesrId, Long itemId, Long storeId, int count) {
        User user = userRepository.findOne(uesrId);
        Item item = itemRepository.findOne(itemId);
        Store store = storeRepository.findById(storeId);

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(user, store, orderItem);

        orderRepository.save(order);
        return orderItem.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    @Transactional
    public void orderDone(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.setStatus(OrderStatus.DONE);
    }

//    @Transactional
//    public List<StoreOrderDto> getOrdering(Long storeId) {
//        return orderRepository.findOrdering(storeId);
//    }

    @Transactional
    public List<StoreOrderDto> getDone(Long storeId) {
        return orderRepository.findDone(storeId);
    }

    @Transactional
    public void orderRedo(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.setStatus(OrderStatus.ORDER);
    }
}
