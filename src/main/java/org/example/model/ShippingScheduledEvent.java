package org.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Date;

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
