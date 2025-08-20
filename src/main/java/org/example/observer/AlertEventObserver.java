package org.example.observer;

import org.example.domain.OrderStatus;
import org.example.events.Event;
import org.example.domain.Order;

import java.time.format.DateTimeFormatter;
import java.util.Set;

public class AlertEventObserver implements EventObserver {
    private static final Set<OrderStatus> CRITICAL_STATUSES = Set.of(
            OrderStatus.SHIPPED,
            OrderStatus.CANCELLED,
            OrderStatus.FAILED
    );

    @Override
    public void update(Order order, Event event) {
        if (CRITICAL_STATUSES.contains(order.getStatus())) {
            System.out.printf(
                    "ALERT! Order %s has a critical status update: %s%n",
                    order.getOrderId(),
                    order.getStatus()
            );
        }
    }
}
