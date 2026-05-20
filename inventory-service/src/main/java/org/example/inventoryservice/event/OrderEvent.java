package org.example.inventoryservice.event;

import java.time.Instant;

public class OrderEvent {
    private String orderId;
    private String itemId;
    private int quantity;
    private Instant createdAt;

    public OrderEvent() {}

    public OrderEvent(String orderId, String itemId, int quantity, Instant createdAt) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
