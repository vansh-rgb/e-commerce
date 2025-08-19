package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderCreatedEvent extends BaseEvent{

    private String customerId;
    private List<Item> items;
    private double totalAmount;

    public OrderCreatedEvent(String eventId, Date timestamp, String orderId, String customerId, List<Item> items, double totalAmount) {
        super(eventId, timestamp, "OrderCreated", orderId);
        this.customerId = customerId;
        this.items = items;
        this.totalAmount = totalAmount;
    }

}
