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

    private final String orderId;
    private final String customerId;
    private final List<OrderItem> items;
    private final double totalAmount;
    private OrderStatus status;
    private final List<Event> eventHistory;

    public Order(String orderId, String customerId, List<OrderItem> items, double totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.status = OrderStatus.PENDING;
        this.eventHistory = new ArrayList<>();
    }

    public void addEventToHistory(Event event) {
        this.eventHistory.add(event);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", status=" + status +
                ", totalAmount=" + totalAmount +
                ", items=" + items.size() +
                '}';
    }
}
