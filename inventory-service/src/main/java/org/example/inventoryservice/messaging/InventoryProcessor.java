package org.example.inventoryservice.messaging;

import org.example.inventoryservice.event.OrderEvent;
import org.example.inventoryservice.store.InventoryStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryProcessor {
    private static final Logger log = LoggerFactory.getLogger(InventoryProcessor.class);

    private final InventoryStore store;

    public InventoryProcessor(InventoryStore store) {
        this.store = store;
    }

    @KafkaListener(topics = "orders", groupId = "inventory-group")
    public void process(OrderEvent event) {
        log.info("Received order: {}", event.getOrderId());
        boolean reserved = store.reserve(event.getItemId(), event.getQuantity());

        if (reserved) {
            log.info("Order {} processed successfully. Remaining stock for {} = {}",
                    event.getOrderId(),
                    event.getItemId(),
                    store.currentState().get(event.getItemId())
            );
        } else {
            log.warn("Order {} rejected. Not enough stock for {}",
                    event.getOrderId(),
                    event.getItemId()
            );
        }
    }
}
