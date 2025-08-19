package org.example.model;

import java.util.Date;

public interface Event {

    String getEventId();
    Date getTimeStamp();
    String getEventType();
    String getOrderId();

}
