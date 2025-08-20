package org.example.events.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.events.Event;

import java.time.ZonedDateTime;

public class ShippingScheduledEvent extends Event {
    private final String orderId;
    private final ZonedDateTime shippingDate;

    @JsonCreator
    public ShippingScheduledEvent(
            @JsonProperty("eventId") String eventId,
            @JsonProperty("timestamp") ZonedDateTime timestamp,
            @JsonProperty("orderId") String orderId,
            @JsonProperty("shippingDate") ZonedDateTime shippingDate) {
        super(eventId, timestamp);
        this.orderId = orderId;
        this.shippingDate = shippingDate;
    }

    @Override
    public String getOrderId() { return orderId; }
    @Override
    public String getEventType() { return "ShippingScheduled"; }

    public ZonedDateTime getShippingDate() { return shippingDate; }
}
