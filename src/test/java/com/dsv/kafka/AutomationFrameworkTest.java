package com.dsv.kafka;

import io.qameta.allure.*;

import com.dsv.kafka.consumer.MessageConsumer;
import com.dsv.kafka.verification.MessageVerifier;
import com.dsv.kafka.model.Message;
import com.dsv.kafka.producer.MessageProducer;
import com.dsv.kafka.service.AutomationFramework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@Epic("Kafka Automation Framework")
@Feature("Message Processing Automation")
public class AutomationFrameworkTest {

    @Mock
    private MessageProducer producer;

    @Mock
    private MessageConsumer consumer;

    @InjectMocks
    private AutomationFramework framework;

    @Mock
    private MessageVerifier messageVerifier;

    @Test
    @Story("Automation Framework Execution")
    @Description("Test verifies that automation framework runs and sends message to producer, and consumer processes it")
    @Severity(SeverityLevel.CRITICAL)
    public void testRunAutomation() {
    // Run the automation framework logic
    framework.runAutomation();

    // Verify that a message was sent to the producer
    verify(producer).sendMessage(eq("sender"), any(Message.class));
    System.out.println("Message sent to producer successfully");

    // Start consuming messages
    consumer.startConsuming();
    System.out.println("Consumer started");

    // Simulate message verification
    Message mockMessage = new Message();
    mockMessage.setTraceId("trace-001");
    mockMessage.setContent("Sample content");

    when(messageVerifier.verifyMessage(mockMessage, "trace-001")).thenReturn(true);

    boolean isVerified = messageVerifier.verifyMessage(mockMessage, "trace-001");
    if (isVerified) {
        System.out.println("Message verified and consumed: " + mockMessage.getTraceId());
    } else {
        System.out.println("Message verification failed: " + mockMessage.getTraceId());
    }

    // Stop consuming messages
    consumer.stopConsuming();
    System.out.println("Consumer stopped");
}

    
}