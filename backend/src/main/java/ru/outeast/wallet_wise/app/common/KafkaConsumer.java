package ru.outeast.wallet_wise.app.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "db_topic", groupId = "db-consumer-group")
    public void consumeDbTopic(String message) {
        log.info("Received message from db_topic: {}\n--------------------------------", message);

        // Дополнительная логика обработки сообщения из db_topic
    }

    @KafkaListener(topics = "auth_topic", groupId = "auth-consumer-group")
    public void consumeAuthTopic(String message) {
        log.info("Received message from auth_topic: {}\n--------------------------------", message);
        // Дополнительная логика обработки сообщения из auth_topic
    }

    @KafkaListener(topics = "request_topic", groupId = "request-consumer-group")
    public void consumeRequestTopic(String message) {
        log.info("Received message from request_topic: {}\n--------------------------------", message);
        // Дополнительная логика обработки сообщения из request_topic
    }
}
