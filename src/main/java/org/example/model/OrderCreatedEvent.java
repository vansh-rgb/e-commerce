package org.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class OrderCreatedEvent extends Event {
    private final String orderId;
    @Getter
    private final String customerId;
    @Getter
    private final List<OrderItem> items;
    @Getter
    private final double totalAmount;

    @JsonCreator
    public OrderCreatedEvent(
            @JsonProperty("eventId") String eventId,
            @JsonProperty("timestamp") ZonedDateTime timestamp,
            @JsonProperty("orderId") String orderId,
            @JsonProperty("customerId") String customerId,
            @JsonProperty("items") List<OrderItem> items,
            @JsonProperty("totalAmount") double totalAmount) {
        super(eventId, timestamp);
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    @Override
    public String getOrderId() { return orderId; }
    @Override
    public String getEventType() { return "OrderCreated"; }

}
