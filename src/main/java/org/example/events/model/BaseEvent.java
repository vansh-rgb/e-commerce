package org.example.events.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.events.Event;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEvent implements Event {

    protected String eventId;
    protected Date timestamp;
    protected String eventType;
    protected String orderId;

    @Override
    public String getEventId() {
        return eventId;
    }

    @Override
    public Date getTimeStamp() {
        return timestamp;
    }

    @Override
    public String getEventType() {
        return eventType;
    }

    @Override
    public String getOrderId() {
        return orderId;
    }
}
