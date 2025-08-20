package org.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class OrderItem {
    private final String itemId;
    private final int qty;

    @JsonCreator
    public OrderItem(
            @JsonProperty("itemId") String itemId,
            @JsonProperty("qty") int qty) {
        this.itemId = itemId;
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemId='" + itemId + '\'' +
                ", qty=" + qty +
                '}';
    }
}
