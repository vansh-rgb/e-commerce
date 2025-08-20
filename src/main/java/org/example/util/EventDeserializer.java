package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.events.Event;

public class EventDeserializer {

    private static final ObjectMapper objectMapper = createObjectMapper();

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    public static Event deserialize(String jsonEvent) {
        if (jsonEvent == null || jsonEvent.trim().isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(jsonEvent, Event.class);
        } catch (JsonProcessingException e) {
            System.err.println("Failed to deserialize event. Details: " + e.getMessage());
            System.err.println("   Problematic JSON: " + jsonEvent);
            return null;
        }
    }

}
