package org.example.observer;

import org.example.events.model.BaseEvent;
import org.example.domain.Order;

public interface Observer {
    void update(Order order, BaseEvent event);
}
