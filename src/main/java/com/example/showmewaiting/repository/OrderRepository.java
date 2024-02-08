package com.example.showmewaiting.repository;

import com.example.showmewaiting.domain.*;
import com.example.showmewaiting.dto.OrderDto;
import com.example.showmewaiting.dto.StoreOrderDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<OrderDto> findOrderDtos() {
        return em.createQuery(
                "select new com.example.showmewaiting.dto.OrderDto(o.id, u.id, s.id, o.orderDate, o.status)" +
                        " from Order o" +
                        " join o.user u" +
                        " join o.store s", OrderDto.class)
                .getResultList();
    }

    public List<StoreOrderDto> findOrdering(Long id) {
        return em.createQuery(
            "select new com.example.showmewaiting.dto.StoreOrderDto(o.id, u.name, s.name, oi.name, oi.count, oi.orderPrice, o.orderDate, o.status)" +
                        " from Order o" +
                        " join o.user u" +
                        " join o.store s" +
                        " join o.orderItems oi" +
                        " where o.store.id = :id and" +
                        " o.status = 'ORDER'", StoreOrderDto.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<StoreOrderDto> findDone(Long id) {
        return em.createQuery(
                        "select new com.example.showmewaiting.dto.StoreOrderDto(o.id, u.name, s.name, oi.name, oi.count, oi.orderPrice, o.orderDate, o.status)" +
                                " from Order o" +
                                " join o.user u" +
                                " join o.store s" +
                                " join o.orderItems oi" +
                                " where o.store.id = :id and" +
                                " o.status = 'DONE'" , StoreOrderDto.class)
                .setParameter("id", id)
                .getResultList();
    }
}
