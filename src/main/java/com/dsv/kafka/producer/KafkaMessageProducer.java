package com.dsv.kafka.producer;

import com.dsv.kafka.model.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class KafkaMessageProducer implements MessageProducer {
    
    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageProducer.class);
    
    private final KafkaTemplate<String, Message> kafkaTemplate;
    
    public KafkaMessageProducer(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    
    @Override
    public void sendMessage(String topic, Message message) {
        try {
            org.springframework.messaging.Message<Message> kafkaMessage = MessageBuilder
                .withPayload(message)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .setHeader("traceId", message.getTraceId())
                .setHeader("messageId", message.getId())
                .build();
                
            kafkaTemplate.send(kafkaMessage);
            logger.info("Message sent to topic: {} with traceId: {}", topic, message.getTraceId());
        } catch (Exception e) {
            logger.error("Failed to send message", e);
            throw new RuntimeException("Message sending failed", e);
        }
    }
}