package org.example.processor;

import org.example.domain.Order;
import org.example.domain.OrderStatus;
import org.example.events.Event;
import org.example.events.model.*;
import org.example.observer.EventObserver;
import org.example.observer.EventPublisher;
import org.example.repository.OrderRepository;

import java.util.*;


public class EventProcessor {

    private final OrderRepository orderRepository;
    private final EventPublisher eventPublisher;

    public EventProcessor(OrderRepository orderRepository, EventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.eventPublisher = eventPublisher;
    }

    public void processEvents(List<Event> events) {
        events.forEach(this::process);
    }

    private void process(Event event) {
        switch (event) {
            case OrderCreatedEvent orderCreatedEvent -> handleOrderCreated(orderCreatedEvent);
            case PaymentReceivedEvent paymentReceivedEvent -> handlePaymentReceived(paymentReceivedEvent);
            case ShippingScheduledEvent shippingScheduledEvent -> handleShippingScheduled(shippingScheduledEvent);
            case OrderCancelledEvent orderCancelledEvent -> handleOrderCancelled(orderCancelledEvent);
            case null, default -> {
                assert event != null;
                handleUnknownEvent(event);
            }
        }
    }

    private void handleOrderCreated(OrderCreatedEvent event) {
        Order order = new Order(
                event.getOrderId(),
                event.getCustomerId(),
                event.getItems(),
                event.getTotalAmount()
        );
        updateOrderStateAndNotify(order, OrderStatus.PENDING, event);
    }

    private void handlePaymentReceived(PaymentReceivedEvent event) {
        findOrder(event.getOrderId()).ifPresent(order -> {
            OrderStatus newStatus;
            if (event.getAmountPaid() >= order.getTotalAmount()) {
                newStatus = OrderStatus.PAID;
            } else if (event.getAmountPaid() > 0) {
                newStatus = OrderStatus.PARTIALLY_PAID;
            } else {
                newStatus = OrderStatus.FAILED;
            }
            updateOrderStateAndNotify(order, newStatus, event);
        });
    }

    private void handleShippingScheduled(ShippingScheduledEvent event) {
        findOrder(event.getOrderId()).ifPresent(order ->
                updateOrderStateAndNotify(order, OrderStatus.SHIPPED, event)
        );
    }

    private void handleOrderCancelled(OrderCancelledEvent event) {
        findOrder(event.getOrderId()).ifPresent(order ->
                updateOrderStateAndNotify(order, OrderStatus.CANCELLED, event)
        );
    }

    private void handleUnknownEvent(Event event) {
        System.err.println("Warning: Unknown event type '" + event.getEventType() + "' for event ID: " + event.getEventId());
    }

    private Optional<Order> findOrder(String orderId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isEmpty()) {
            System.err.println("Warning: Order with ID '" + orderId + "' not found for processing event.");
        }
        return orderOpt;
    }

    private void updateOrderStateAndNotify(Order order, OrderStatus newStatus, Event event) {
        order.setStatus(newStatus);
        order.addEventToHistory(event);
        orderRepository.save(order);
        eventPublisher.notify(event, order);
    }

}
