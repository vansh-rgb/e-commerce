package org.example.events.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.events.Event;

import java.time.ZonedDateTime;

public class OrderCancelledEvent extends Event {
    private final String orderId;
    private final String reason;

    @JsonCreator
    public OrderCancelledEvent(
            @JsonProperty("eventId") String eventId,
            @JsonProperty("timestamp") ZonedDateTime timestamp,
            @JsonProperty("orderId") String orderId,
            @JsonProperty("reason") String reason) {
        super(eventId, timestamp);
        this.orderId = orderId;
        this.reason = reason;
    }

    @Override
    public String getOrderId() { return orderId; }
    @Override
    public String getEventType() { return "OrderCancelled"; }

    public String getReason() { return reason; }
}
