package org.example.events.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.example.events.Event;

import java.time.ZonedDateTime;

public class PaymentReceivedEvent extends Event {
    private final String orderId;
    @Getter
    private final double amountPaid;

    @JsonCreator
    public PaymentReceivedEvent(
            @JsonProperty("eventId") String eventId,
            @JsonProperty("timestamp") ZonedDateTime timestamp,
            @JsonProperty("orderId") String orderId,
            @JsonProperty("amountPaid") double amountPaid) {
        super(eventId, timestamp);
        this.orderId = orderId;
        this.amountPaid = amountPaid;
    }

    @Override
    public String getOrderId() { return orderId; }
    @Override
    public String getEventType() { return "PaymentReceived"; }

}
