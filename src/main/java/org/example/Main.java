package org.example;


import org.example.events.Event;
import org.example.observer.AlertEventObserver;
import org.example.observer.EventPublisher;
import org.example.observer.LoggerEventObserver;
import org.example.processor.EventProcessor;
import org.example.repository.OrderRepository;
import org.example.util.EventParser;

import java.net.URISyntaxException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws URISyntaxException {

        System.out.println("--- Order Processing System Simulation Starting ---");

        EventParser eventReader = new EventParser();
        OrderRepository orderRepository = new OrderRepository();
        EventPublisher eventPublisher = new EventPublisher();
        EventProcessor eventProcessor = new EventProcessor(orderRepository, eventPublisher);

        LoggerEventObserver loggerObserver = new LoggerEventObserver();
        AlertEventObserver alertObserver = new AlertEventObserver();

        eventPublisher.subscribe(loggerObserver);
        eventPublisher.subscribe(alertObserver);

        List<Event> events = eventReader.readEvents("events.txt");

        if (!events.isEmpty()) {
            System.out.println("\n--- Processing " + events.size() + " events ---");
            eventProcessor.processEvents(events);
        }

        System.out.println("\n--- Order Processing System Simulation Finished ---");
    }
}