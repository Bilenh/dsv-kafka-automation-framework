package com.dsv.kafka.service;

import com.dsv.kafka.consumer.MessageConsumer;
import com.dsv.kafka.model.Message;
import com.dsv.kafka.producer.MessageProducer;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.qameta.allure.Step;

import java.util.UUID;

@Service
public class AutomationFramework {

    private static final Logger logger = LoggerFactory.getLogger(AutomationFramework.class);

    private final MessageProducer producer;
    private final MessageConsumer consumer;

    public AutomationFramework(MessageProducer producer, MessageConsumer consumer) {
        this.producer = producer;
        this.consumer = consumer;
    }

    @Step("Run automation framework")
    public void runAutomation() {
        // Start consuming
        consumer.startConsuming();
        logger.info("Consumer started");

        // Create and send sample message
        String traceId = UUID.randomUUID().toString();
        Message sampleMessage = new Message(
            UUID.randomUUID().toString(),
            "Bilen's sample message",
            traceId
        );

        logger.info("Sending message with traceId: {}", traceId);
        producer.sendMessage("sender", sampleMessage);
        logger.info("Sending message with content: {}", sampleMessage);

        // In a real-world scenario, we might wait for confirmation or verification
        // For simplicity, just log that we sent and assume consumer processes it
        logger.info("Message sent, awaiting consumption...");

        // Stop consuming (simulate end of cycle)
        consumer.stopConsuming();
        logger.info("Consumer stopped");
    }

    @Step("Stop automation framework")
    public void stopAutomation() {
        consumer.stopConsuming();
        logger.info("Consumer stopped manually via stopAutomation()");
    }
}