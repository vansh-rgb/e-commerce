package org.example;


import org.example.model.BaseEvent;
import org.example.observer.AlertObserver;
import org.example.observer.LoggerObserver;
import org.example.processor.EventProcessor;
import org.example.util.EventParser;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws URISyntaxException {

        EventParser parser = new EventParser();
        EventProcessor processor = new EventProcessor();

        LoggerObserver loggerObserver = new LoggerObserver();
        AlertObserver alertObserver = new AlertObserver();

        processor.addObserver(loggerObserver);
        processor.addObserver(alertObserver);

        String filePath = System.getProperty("user.dir") + "/src/main/resources/events.txt";
        List<BaseEvent> events = parser.readFromFileEvent(filePath);

        if (events.isEmpty()) {
            System.out.println("No events found to process.");
            return;
        }

        System.out.println("Starting event processing for " + events.size() + " events.");

        processor.processEvents(events);

        System.out.println("Event processing finished.");
    }
}