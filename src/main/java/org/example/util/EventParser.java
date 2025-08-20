package org.example.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.example.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class EventParser {

    public List<Event> readEvents(String resourceFileName) {
        System.out.println("🚀 Starting event ingestion from file: " + resourceFileName);

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourceFileName)) {
            if (is == null) {
                System.err.println("❌ Critical Error: Resource file not found: " + resourceFileName);
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
