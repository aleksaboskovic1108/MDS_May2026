# Event Driven Order Processing System

## Architecture

- order-service
- inventory-service
- Kafka broker
- Zookeeper

## Tech Stack

- Java 17
- Spring Boot
- Apache Kafka
- Docker Compose

## Running

docker compose up - docker run (local option)
mvn clean install & mvn spring-boot:run - services run (local option)

## Testing

(local option)
curl --location 'http://localhost:8080/orders' \
--header 'Content-Type: application/json' \
--data '{
"orderId": "1234",
"itemId": "DIET COKE",
"quantity": 5
}'

## Assumptions

- Inventory stored in-memory
- At-least-once delivery
- Single Kafka topic

## Future Improvements

- DB persistence
- Retry topics
- Lombok for getters/setters