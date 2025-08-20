package org.example.observer;

import org.example.events.Event;
import org.example.domain.Order;

public interface EventObserver {
    void update(Order order, Event event);
}
