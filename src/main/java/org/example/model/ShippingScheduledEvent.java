package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class ShippingScheduledEvent extends BaseEvent{

    private Date shippingDate;

    public ShippingScheduledEvent(String eventId, Date timestamp, String orderId, Date shippingDate) {
        super(eventId, timestamp, "ShippingScheduled", orderId);
        this.shippingDate = shippingDate;
    }

}
