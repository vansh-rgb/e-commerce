package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Order {

    private String orderId;

    private String customerId;

    private List<Item> items;

    private double totalAmount;

    private OrderStatus orderStatus;

    private List<String> eventHistory;

    public Order(String orderId, String customerId, List<Item> items, double totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.orderStatus = OrderStatus.PENDING;
        this.eventHistory = new ArrayList<>();
    }

    public void addEventToHistory(String eventId) {
        this.eventHistory.add(eventId);
    }
}
