package org.example.orderservice.messaging;

import org.example.orderservice.event.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {
    private static final Logger log = LoggerFactory.getLogger(OrderProducer.class);

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(OrderEvent event) {
        try {
            kafkaTemplate.send("orders", event.getOrderId(), event);
            log.info("Order event sent: {}", event.getOrderId());
        } catch (Exception e) {
            log.error("Failed to send event {}", event.getOrderId(), e);
        }
    }
}