package org.example.observer;

import org.example.model.BaseEvent;
import org.example.model.Order;

public class LoggerObserver implements Observer{
    @Override
    public void update(Order order, BaseEvent event) {
        System.out.println("--- LOG: Status Change ---");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("New Status: " + order.getOrderStatus());
        System.out.println("Event Type: " + event.getEventType());
        System.out.println("Event ID: " + event.getEventId());
        System.out.println("--------------------------");
    }
}
