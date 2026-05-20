package org.example.inventoryservice.store;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InventoryStore {
    private final Map<String, Integer> inventory = new ConcurrentHashMap<>();

    /* Simulated inventory storage with made up inventory as an example */
    @PostConstruct
    public void init() {
        inventory.put("DIET COKE", 10);
        inventory.put("PEPSI LIME", 5);
        inventory.put("ICED MATCHA", 3);
        inventory.put("TAYLOR SWIFT THE ERAS TOUR T SHIRT", 1);
    }

    public synchronized boolean reserve(String itemId, int quantity) {
        Integer available = inventory.getOrDefault(itemId, 0);
        if (available < quantity) {
            return false;
        }
        inventory.put(itemId, available - quantity);
        return true;
    }

    public Map<String, Integer> currentState() {
        return inventory;
    }
}
