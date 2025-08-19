package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class OrderCancelledEvent extends BaseEvent {
    private String reason;

    public OrderCancelledEvent(String eventId, Date timestamp, String orderId, String reason) {
        super(eventId, timestamp, "OrderCancelled", orderId);
        this.reason = reason;
    }
}
