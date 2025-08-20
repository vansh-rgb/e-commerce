package org.example.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.example.events.model.OrderCancelledEvent;
import org.example.events.model.OrderCreatedEvent;
import org.example.events.model.PaymentReceivedEvent;
import org.example.events.model.ShippingScheduledEvent;

import java.time.ZonedDateTime;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "eventType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OrderCreatedEvent.class, name = "OrderCreated"),
        @JsonSubTypes.Type(value = PaymentReceivedEvent.class, name = "PaymentReceived"),
        @JsonSubTypes.Type(value = ShippingScheduledEvent.class, name = "ShippingScheduled"),
        @JsonSubTypes.Type(value = OrderCancelledEvent.class, name = "OrderCancelled")
})
public abstract class Event {

    private final String eventId;
    private final ZonedDateTime timestamp;

    public Event(String eventId, ZonedDateTime timestamp) {
        this.eventId = eventId;
        this.timestamp = timestamp;
    }

    public String getEventId() {
        return eventId;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public abstract String getOrderId();
    public abstract String getEventType();

}
