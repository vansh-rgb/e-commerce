package org.example.repository;

import org.example.domain.Order;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class OrderRepository {
    private final Map<String, Order> orderStore = new ConcurrentHashMap<>();

    public void save(Order order) {
        orderStore.put(order.getOrderId(), order);
    }

    public Optional<Order> findById(String orderId) {
        return Optional.ofNullable(orderStore.get(orderId));
    }
}
