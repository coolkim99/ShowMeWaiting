package com.example.showmewaiting.repository;

import com.example.showmewaiting.domain.Order;
import com.example.showmewaiting.dto.OrderDto;
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

}
