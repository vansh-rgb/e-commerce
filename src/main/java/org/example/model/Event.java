package org.example.model;

import java.time.ZonedDateTime;
import java.util.Date;

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
