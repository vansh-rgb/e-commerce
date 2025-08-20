package org.example.util;


import org.example.events.Event;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EventParser {

    public List<Event> readEvents(String resourceFileName) {
        System.out.println("Starting event ingestion from file: " + resourceFileName);

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourceFileName)) {
            if (is == null) {
                System.err.println("Critical Error: Resource file not found: " + resourceFileName);
                throw new IllegalArgumentException("File not found in resources: " + resourceFileName);
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                List<Event> events = reader.lines()
                        .map(EventDeserializer::deserialize)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

                System.out.println("✅ Ingestion complete. Successfully parsed " + events.size() + " events.");
                return events;
            }

        } catch (IOException e) {
            System.err.println("Critical Error: Could not read event file: " + resourceFileName);
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
