package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PaymentReceivedEvent extends BaseEvent{

    private double amountPaid;

    public PaymentReceivedEvent(String eventId, Date timestamp, String orderId, double amountPaid) {
        super(eventId, timestamp, "PaymentReceived", orderId);
        this.amountPaid = amountPaid;
    }

}
