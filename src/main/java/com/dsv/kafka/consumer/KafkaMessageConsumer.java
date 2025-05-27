package com.dsv.kafka.consumer;

import com.dsv.kafka.model.Message;
import com.dsv.kafka.verification.MessageVerifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class KafkaMessageConsumer implements MessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageConsumer.class);

    private final MessageVerifier messageVerifier;
    private final AtomicBoolean consuming = new AtomicBoolean(false);

    public KafkaMessageConsumer(MessageVerifier messageVerifier) {
        this.messageVerifier = messageVerifier;
    }

    @Override
    public void startConsuming() {
        consuming.set(true);
        logger.info("Kafka consumer started.");
    }

    @Override
    public void stopConsuming() {
        consuming.set(false);
        logger.info("Kafka consumer stopped.");
    }

    @KafkaListener(topics = "receiver", groupId = "automation-framework", containerFactory = "kafkaListenerContainerFactory")
    public void consume(@Payload Message message, @Header Map<String, Object> headers) {
        if (!consuming.get()) {
            logger.debug("Message received but consumer is inactive. Skipping.");
            return;
        }

        logger.info("Received message: {}", message);

        String headerTraceId = (String) headers.get("traceId");

        if (messageVerifier.verifyMessage(message, headerTraceId)) {
            logger.info("Message verified: {}", message.getTraceId());
            processMessage(message);
            stopConsuming(); // Stop after successfully processing one message
        } else {
            logger.warn("Message verification failed for traceId: {}", message.getTraceId());
        }
    }

    private void processMessage(Message message) {
        logger.info("Processing message with traceId: {}", message.getTraceId());
        // Custom processing logic can go here
    }

    @Override
    public Message receiveMessage(String topic) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'receiveMessage'");
    }
}