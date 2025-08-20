package org.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Date;

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
