package org.example.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.example.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class EventParser {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public EventParser() {
        SimpleModule module = new SimpleModule();

        module.addAbstractTypeMapping(BaseEvent.class, OrderCreatedEvent.class);
        module.addAbstractTypeMapping(BaseEvent.class, PaymentReceivedEvent.class);
        module.addAbstractTypeMapping(BaseEvent.class, ShippingScheduledEvent.class);
        module.addAbstractTypeMapping(BaseEvent.class, OrderCancelledEvent.class);

        objectMapper.registerModule(module);
    }

    public List<BaseEvent> readFromFileEvent(String path) {
        List<BaseEvent> events = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while((line = reader.readLine()) != null ) {
                BaseEvent event = objectMapper.readValue(line, BaseEvent.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }
}
