package org.example.observer;

import org.example.events.Event;
import org.example.domain.Order;

import java.time.format.DateTimeFormatter;

public class LoggerEventObserver implements EventObserver {
    @Override
    public void update(Order order, Event event) {
        String timestamp = event.getTimestamp().format(DateTimeFormatter.ISO_INSTANT);
        System.out.printf(
                "[LOG] Event: %s | OrderID: %s | New Status: %s%n",
                event.getEventType(),
                order.getOrderId(),
                order.getStatus()
        );
    }
}
