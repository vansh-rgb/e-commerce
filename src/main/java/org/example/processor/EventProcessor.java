package org.example.processor;

import org.example.model.*;

import java.util.*;

public class EventProcessor {

    private final Map<String, Order> orders = new HashMap<>();

    public void processEvents(List<BaseEvent> events) {
        for(BaseEvent event: events) {
            System.out.println("Processing Event: " + event.getEventId() + "(" + event.getEventType() + ")");

            Order order = orders.get(event.getOrderId());

            switch (event.getEventType()) {
                case "OrderCreated":
                    handleOrderCreatedEvent((OrderCreatedEvent) event);
                    break;
                case "PaymentReceived":
                    handlePaymentReceivedEvent((PaymentReceivedEvent) event);
                    break;
                case "ShippingScheduled":
                    handleShippingScheduledEvent((ShippingScheduledEvent) event);
                    break;
                case "OrderCancelled":
                    handleOrderCancelledEvent((OrderCancelledEvent) event);
                    break;
                default:
                    System.err.println("Unknown Event Type: " + event.getEventType());
            }
            if(order!=null) {
                notifyObservers(order, event);
            }
        }
    }

    private void handleOrderCreatedEvent(OrderCreatedEvent event) {
        Order newOrder = new Order(
                event.getOrderId(),
                event.getCustomerId(),
                event.getItems(),
                event.getTotalAmount()
        );
        orders.put(newOrder.getOrderId(), newOrder);
        newOrder.addEventToHistory(event.getEventId());
        System.out.println("Created new order: " + newOrder.getOrderId());
    }

    private void handlePaymentReceivedEvent(PaymentReceivedEvent event) {
        Order order = orders.get(event.getOrderId());
        if (order != null) {
            if (event.getAmountPaid() >= order.getTotalAmount()) {
                order.setOrderStatus(OrderStatus.PAID);
                order.addEventToHistory(event.getEventId());
                System.out.println("Order " + order.getOrderId() + " status updated to PAID.");
            } else {
                System.out.println("Payment received for order " + order.getOrderId() + " is partial. Status remains PENDING.");
            }
        } else {
            System.err.println("Order not found for PaymentReceivedEvent: " + event.getOrderId());
        }
    }

    private void handleShippingScheduledEvent(ShippingScheduledEvent event) {
        Order order = orders.get(event.getOrderId());
        if (order != null) {
            order.setOrderStatus(OrderStatus.SHIPPED);
            order.addEventToHistory(event.getEventId());
            System.out.println("Order " + order.getOrderId() + " status updated to SHIPPED.");
        } else {
            System.err.println("Order not found for ShippingScheduledEvent: " + event.getOrderId());
        }
    }

    private void handleOrderCancelledEvent(OrderCancelledEvent event) {
        Order order = orders.get(event.getOrderId());
        if (order != null) {
            order.setOrderStatus(OrderStatus.CANCELLED);
            order.addEventToHistory(event.getEventId());
            System.out.println("Order " + order.getOrderId() + " status updated to CANCELLED.");
        } else {
            System.err.println("Order not found for OrderCancelledEvent: " + event.getOrderId());
        }
    }

}
