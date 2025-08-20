package org.example.observer;

import org.example.domain.Order;
import org.example.events.Event;

import java.util.ArrayList;
import java.util.List;

public class EventPublisher {
    private final List<EventObserver> observers = new ArrayList<>();

    public void subscribe(EventObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(EventObserver observer) {
        observers.remove(observer);
    }

    public void notify(Event event, Order order) {
        for (EventObserver observer : observers) {
            observer.update(order, event);
        }
    }
}