package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Order {

    private String orderId;

    private String customerId;

    private List<Item> items;

    private double totalAmount;

    private OrderStatus orderStatus;

    private List<String> eventHistory;
}
