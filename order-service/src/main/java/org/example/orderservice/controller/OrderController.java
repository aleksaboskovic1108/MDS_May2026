package org.example.orderservice.controller;

import jakarta.validation.Valid;
import org.example.orderservice.dto.OrderRequest;
import org.example.orderservice.event.OrderEvent;
import org.example.orderservice.messaging.OrderProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderProducer producer;

    public OrderController(OrderProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@Valid @RequestBody OrderRequest request) {
        try {
            OrderEvent event = new OrderEvent(
                    request.getOrderId(),
                    request.getItemId(),
                    request.getQuantity(),
                    Instant.now()
            );
            producer.send(event);
            return ResponseEntity.ok("Order received");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order not received - bad request");
        }
    }
}