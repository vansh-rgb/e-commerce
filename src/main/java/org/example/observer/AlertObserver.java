package org.example.observer;

import org.example.model.BaseEvent;
import org.example.model.Order;

public class AlertObserver implements Observer{
    @Override
    public void update(Order order, BaseEvent event) {
        if (order.getOrderStatus().toString().equals("PAID") || order.getOrderStatus().toString().equals("CANCELLED")) {
            System.out.println("!!!!ALERT: Significant Status Change!!!!");
            System.out.println("Sending alert for Order " + order.getOrderId() + ": Status changed to " + order.getOrderStatus());
            System.out.println("--------------------------");
        }
    }
}
